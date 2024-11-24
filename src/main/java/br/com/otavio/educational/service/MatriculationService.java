package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.ClassDto;
import br.com.otavio.educational.dto.MatriculationDto;
import br.com.otavio.educational.dto.MatriculationDto;
import br.com.otavio.educational.dto.StudentDto;
import br.com.otavio.educational.model.ClassModel;
import br.com.otavio.educational.model.MatriculationModel;
import br.com.otavio.educational.model.MatriculationModel;
import br.com.otavio.educational.model.StudentModel;
import br.com.otavio.educational.repository.MatriculationRepository;
import br.com.otavio.educational.service.exception.ResourceCodeAlreadyExistsException;
import br.com.otavio.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MatriculationService {

    private final MatriculationRepository matriculationRepository;
    private final StudentService studentService;
    private final ClassService classService;

    public MatriculationService(MatriculationRepository matriculationRepository, StudentService studentService, ClassService classService) {
        this.matriculationRepository = matriculationRepository;
        this.studentService = studentService;
        this.classService = classService;
    }

    public void createdMatriculation (MatriculationDto matriculationDto) {
        StudentDto studentDto = getStudent(matriculationDto);
        ClassDto classDto = getClass(matriculationDto);

        verifyStudentIsMatriculate(studentDto, classDto);

        matriculationDto.setStudentDto(studentDto);
        matriculationDto.setClassDto(classDto);

        MatriculationModel matriculationModel = CONVERT_DTO_TO_MODEL(matriculationDto);

        matriculationRepository.save(matriculationModel);
    }

    public Set<MatriculationDto> findAllMatrilations () {
        List<MatriculationModel> matriculationModelList = matriculationRepository.findAll();
        Set<MatriculationDto> matriculationDtoSet = new HashSet<>();

        for(MatriculationModel matriculationModel : matriculationModelList) {
            matriculationDtoSet.add(CONVERT_MODEL_TO_DTO(matriculationModel));
        }

        return matriculationDtoSet;
    }

    public MatriculationDto findById (Integer id) {
        MatriculationModel matriculationModel = matriculationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));

        return CONVERT_MODEL_TO_DTO(matriculationModel);
    }

    public void updateMatriculation (Integer id, MatriculationDto matriculationDto) {
        MatriculationDto matriculationDtoOld = findById(id);
        StudentDto studentDto = getStudent(matriculationDto);
        ClassDto classDto = getClass(matriculationDto);

        if(!(matriculationDto.getClassId().equals(matriculationDtoOld.getClassId()) || matriculationDto.getStudentId().equals(matriculationDtoOld.getStudentId()))) {
            verifyStudentIsMatriculate(studentDto, classDto);
        }

        matriculationDto.setStudentDto(studentDto);
        matriculationDto.setClassDto(classDto);
        matriculationDto.setId(id);

        matriculationRepository.save(CONVERT_DTO_TO_MODEL(matriculationDto));

    }

    public void delete (Integer id) {
        MatriculationDto matriculationDto = findById(id);
        matriculationRepository.delete(CONVERT_DTO_TO_MODEL(matriculationDto));
    }

    private void verifyStudentIsMatriculate(StudentDto studentDto, ClassDto classDto) {
        StudentModel studentModel = StudentService.CONVERT_DTO_TO_MODEL(studentDto);
        ClassModel classModel = ClassService.CONVERT_DTO_TO_MODEL(classDto);

        Optional<MatriculationModel> optionalMatriculationModel = matriculationRepository.findByStudentModelAndClassModel(studentModel, classModel);

        if(optionalMatriculationModel.isPresent()) {
            throw new ResourceCodeAlreadyExistsException("Esse aluno já esta matriculado nesta turma");
        }
    }

    private StudentDto getStudent (MatriculationDto matriculationDto) {
        StudentDto studentDto;
        try {
            studentDto = studentService.findById(matriculationDto.getStudentId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return studentDto;
    }

    private ClassDto getClass(MatriculationDto matriculationDto) {
        ClassDto classDto;
        try {
            classDto = classService.findById(matriculationDto.getClassId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return classDto;
    }

    public static MatriculationModel CONVERT_DTO_TO_MODEL(MatriculationDto matriculationDto) {
        MatriculationModel matriculationModel = new MatriculationModel();
        matriculationModel.setId(matriculationDto.getId() != null ? matriculationDto.getId() : null);
        matriculationModel.setStudentModel(matriculationDto.getStudentDto() != null ? StudentService.CONVERT_DTO_TO_MODEL(matriculationDto.getStudentDto()) : null);
        matriculationModel.setClassModel(matriculationDto.getClassDto() != null ? ClassService.CONVERT_DTO_TO_MODEL(matriculationDto.getClassDto()) : null);

        return matriculationModel;
    }

    public static MatriculationDto CONVERT_MODEL_TO_DTO(MatriculationModel matriculationModel) {
        MatriculationDto matriculationDto = new MatriculationDto();
        matriculationDto.setId(matriculationModel.getId() != null ? matriculationModel.getId() : null);
        matriculationDto.setStudentId(matriculationModel.getStudentModel().getId() != null ? matriculationModel.getStudentModel().getId() : null);
        matriculationDto.setClassId(matriculationModel.getClassModel().getId() != null ? matriculationModel.getClassModel().getId() : null);
        matriculationDto.setStudentDto(matriculationModel.getStudentModel() != null ? StudentService.CONVERT_MODEL_TO_DTO(matriculationModel.getStudentModel()) : null);
        matriculationDto.setClassDto(matriculationModel.getClassModel() != null ? ClassService.CONVERT_MODEL_TO_DTO(matriculationModel.getClassModel()) : null);

        return matriculationDto;
    }
}
