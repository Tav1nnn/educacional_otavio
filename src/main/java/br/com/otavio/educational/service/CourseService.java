package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.CourseDto;
import br.com.otavio.educational.model.CourseModel;
import br.com.otavio.educational.repository.CourseRepository;
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

        CourseModel courseModel = convertDtoToModel(courseDto);

        courseRepository.save(courseModel);
    }

    public void updateCourse(CourseDto courseDto, Integer id) {
        
        CourseModel courseModel = convertDtoToModel(courseDto);
        courseModel.setId(id);
        courseRepository.save(courseModel);
    }

    public CourseDto findById (Integer id) {
        CourseModel courseModel = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return convertModelToDto(courseModel);
    }

    public List<CourseDto> findAll () {
        List<CourseModel> lstCourseModel = courseRepository.findAll();
        List<CourseDto> lstCourseDto = new ArrayList<>();

        for(CourseModel courseModel : lstCourseModel) {
            lstCourseDto.add(convertModelToDto(courseModel));
        }

        return lstCourseDto;
    }

    public void deleteCourse(Integer id) {
        CourseDto verifyCourseDto = findById(id);
        CourseModel courseModel = convertDtoToModel(verifyCourseDto);
        courseModel.setId(id);
        courseRepository.delete(courseModel);
    }

    private CourseModel convertDtoToModel(CourseDto courseDto) {
        CourseModel courseModel = new CourseModel();
        courseModel.setId(courseDto.getId() != null ? courseDto.getId() : null);
        courseModel.setName(courseDto.getName());
        courseModel.setCode(courseDto.getCode());
        courseModel.setWorkload(courseDto.getWorkload());

        return courseModel;
    }

    private CourseDto convertModelToDto(CourseModel courseModel) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(courseModel.getId() != null ? courseModel.getId() : null);
        courseDto.setName(courseModel.getName());
        courseDto.setCode(courseModel.getCode());
        courseDto.setWorkload(courseModel.getWorkload());

        return courseDto;
    }

}
