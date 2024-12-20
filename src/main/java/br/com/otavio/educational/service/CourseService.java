package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.CourseDto;
import br.com.otavio.educational.model.CourseModel;
import br.com.otavio.educational.model.StudentModel;
import br.com.otavio.educational.repository.CourseRepository;
import br.com.otavio.educational.service.exception.ResourceCodeAlreadyExistsException;
import br.com.otavio.educational.service.exception.ResourceEmailAlreadyExistsException;
import br.com.otavio.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(CourseDto courseDto) {

        verifyCode(courseDto);

        CourseModel courseModel = CONVERT_DTO_TO_MODEL(courseDto);

        courseRepository.save(courseModel);
    }

    public void updateCourse(CourseDto courseDto, Integer id) {
        CourseDto verifyCourseDto = findById(id);

        if(!verifyCourseDto.getCode().equals(courseDto.getCode())) {
            verifyCode(courseDto);
        }
        
        CourseModel courseModel = CONVERT_DTO_TO_MODEL(courseDto);
        courseModel.setId(id);
        courseRepository.save(courseModel);
    }

    public CourseDto findById (Integer id) {
        CourseModel courseModel = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return CONVERT_MODEL_TO_DTO(courseModel);
    }

    public List<CourseDto> findAll () {
        List<CourseModel> lstCourseModel = courseRepository.findAll();
        List<CourseDto> lstCourseDto = new ArrayList<>();

        for(CourseModel courseModel : lstCourseModel) {
            lstCourseDto.add(CONVERT_MODEL_TO_DTO(courseModel));
        }

        return lstCourseDto;
    }

    public void deleteCourse(Integer id) {
        CourseDto verifyCourseDto = findById(id);
        CourseModel courseModel = CONVERT_DTO_TO_MODEL(verifyCourseDto);
        courseModel.setId(id);
        courseRepository.delete(courseModel);
    }

    private void verifyCode (CourseDto courseDto) {
        Optional<CourseModel> courseModelOptional = courseRepository.findByCode(courseDto.getCode());

        if(courseModelOptional.isPresent()) {
            throw new ResourceCodeAlreadyExistsException("Já existe um curso com esse código");
        }
    }

    public static CourseModel CONVERT_DTO_TO_MODEL(CourseDto courseDto) {
        CourseModel courseModel = new CourseModel();
        courseModel.setId(courseDto.getId() != null ? courseDto.getId() : null);
        courseModel.setName(courseDto.getName());
        courseModel.setCode(courseDto.getCode());
        courseModel.setWorkload(courseDto.getWorkload());

        return courseModel;
    }

    public static CourseDto CONVERT_MODEL_TO_DTO(CourseModel courseModel) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(courseModel.getId() != null ? courseModel.getId() : null);
        courseDto.setName(courseModel.getName());
        courseDto.setCode(courseModel.getCode());
        courseDto.setWorkload(courseModel.getWorkload());

        return courseDto;
    }

}
