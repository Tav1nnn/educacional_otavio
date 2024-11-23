package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.ClassDto;
import br.com.otavio.educational.dto.CourseDto;
import br.com.otavio.educational.model.ClassModel;
import br.com.otavio.educational.repository.ClassRepository;
import br.com.otavio.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    private final CourseService courseService;

    public ClassService(ClassRepository classRepository, CourseService courseService) {
        this.classRepository = classRepository;
        this.courseService = courseService;
    }

    public void createClass(ClassDto classDto) {

        CourseDto courseDto = getCourseDto(classDto);

        classDto.setCourseDto(courseDto);

        ClassModel classModel = CONVERT_DTO_TO_MODEL(classDto);

        classRepository.save(classModel);
    }

    public void updateClass(ClassDto classDto, Integer id) {

        CourseDto courseDto = getCourseDto(classDto);

        classDto.setCourseDto(courseDto);

        ClassModel classModel = CONVERT_DTO_TO_MODEL(classDto);
        classModel.setId(id);
        classRepository.save(classModel);
    }

    public ClassDto findById (Integer id) {
        ClassModel classModel = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return CONVERT_MODEL_TO_DTO(classModel);
    }

    public Set<ClassDto> searchClass (Integer year, Integer semester, Integer courseId) {
        Set<ClassModel> lstClassModel = classRepository.searchClasses(year, semester, courseId);
        Set<ClassDto> lstClassDto = new HashSet<>();

        for(ClassModel classModel : lstClassModel) {
            lstClassDto.add(CONVERT_MODEL_TO_DTO(classModel));
        }

        return lstClassDto;
    }

    public void deleteClass(Integer id) {
        ClassDto verifyClassDto = findById(id);
        ClassModel classModel = CONVERT_DTO_TO_MODEL(verifyClassDto);
        classModel.setId(id);
        classRepository.delete(classModel);
    }

    private CourseDto getCourseDto(ClassDto classDto) {
        CourseDto courseDto;
        try {
            courseDto = courseService.findById(classDto.getCourseId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("curso_id não foi encontrado");
        }
        return courseDto;
    }

    public static ClassModel CONVERT_DTO_TO_MODEL(ClassDto classDto) {
        ClassModel classModel = new ClassModel();
        classModel.setId(classDto.getId() != null ? classDto.getId() : null);
        classModel.setYear(classDto.getYear());
        classModel.setSemester(classDto.getSemester());
        classModel.setCourseModel(classDto.getCourseDto() != null ? CourseService.CONVERT_DTO_TO_MODEL(classDto.getCourseDto()) : null);

        return classModel;
    }

    public static ClassDto CONVERT_MODEL_TO_DTO(ClassModel classModel) {
        ClassDto classDto = new ClassDto();
        classDto.setId(classModel.getId() != null ? classModel.getId() : null);
        classDto.setYear(classModel.getYear());
        classDto.setSemester(classModel.getSemester());
        classDto.setCourseId(classModel.getCourseModel().getId() != null ? classModel.getCourseModel().getId() : null);
        classDto.setCourseDto(classModel.getCourseModel() != null ? CourseService.CONVERT_MODEL_TO_DTO(classModel.getCourseModel()) : null);

        return classDto;
    }

}
