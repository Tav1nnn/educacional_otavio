package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.DisciplineDto;
import br.com.otavio.educational.dto.CourseDto;
import br.com.otavio.educational.dto.ProfessorDto;
import br.com.otavio.educational.model.CourseModel;
import br.com.otavio.educational.model.DisciplineModel;
import br.com.otavio.educational.repository.DisciplineRepository;
import br.com.otavio.educational.service.exception.ResourceCodeAlreadyExistsException;
import br.com.otavio.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final CourseService courseService;
    private final ProfessorService professorService;

    public DisciplineService(DisciplineRepository disciplineRepository, CourseService courseService, ProfessorService professorService) {
        this.disciplineRepository = disciplineRepository;
        this.courseService = courseService;
        this.professorService = professorService;
    }

    public void createDiscipline(DisciplineDto disciplineDto) {

        verifyCode(disciplineDto);

        CourseDto courseDto = getCourseDto(disciplineDto);
        ProfessorDto professorDto = getProfessorDto(disciplineDto);

        disciplineDto.setCourseDto(courseDto);
        disciplineDto.setProfessorDto(professorDto);

        DisciplineModel disciplineModel = CONVERT_DTO_TO_MODEL(disciplineDto);

        disciplineRepository.save(disciplineModel);
    }

    public void updateDiscipline(DisciplineDto disciplineDto, Integer id) {

        DisciplineDto verifyDisciplineDto = findById(id);

        if(!verifyDisciplineDto.getCode().equals(disciplineDto.getCode())) {
            verifyCode(disciplineDto);
        }

        CourseDto courseDto = getCourseDto(disciplineDto);
        ProfessorDto professorDto = getProfessorDto(disciplineDto);

        disciplineDto.setCourseDto(courseDto);
        disciplineDto.setProfessorDto(professorDto);

        DisciplineModel disciplineModel = CONVERT_DTO_TO_MODEL(disciplineDto);
        disciplineModel.setId(id);
        disciplineRepository.save(disciplineModel);
    }

    public DisciplineDto findById (Integer id) {
        DisciplineModel disciplineModel = disciplineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return CONVERT_MODEL_TO_DTO(disciplineModel);
    }

    public Set<DisciplineDto> findAll () {
        List<DisciplineModel> lstDisciplineModel = disciplineRepository.findAll();
        Set<DisciplineDto> lstDisciplineDto = new HashSet<>();

        for(DisciplineModel disciplineModel : lstDisciplineModel) {
            lstDisciplineDto.add(CONVERT_MODEL_TO_DTO(disciplineModel));
        }

        return lstDisciplineDto;
    }

    public void deleteDiscipline(Integer id) {
        DisciplineDto verifyDisciplineDto = findById(id);
        DisciplineModel disciplineModel = CONVERT_DTO_TO_MODEL(verifyDisciplineDto);
        disciplineModel.setId(id);
        disciplineRepository.delete(disciplineModel);
    }

    private void verifyCode (DisciplineDto disciplineDto) {
        Optional<DisciplineModel> disciplineModelOptional = disciplineRepository.findByCode(disciplineDto.getCode());

        if(disciplineModelOptional.isPresent()) {
            throw new ResourceCodeAlreadyExistsException("Já existe uma disciplina com esse código");
        }
    }

    private CourseDto getCourseDto(DisciplineDto disciplineDto) {
        CourseDto courseDto;
        try {
            courseDto = courseService.findById(disciplineDto.getCourseId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("curso_id não foi encontrado");
        }
        return courseDto;
    }

    private ProfessorDto getProfessorDto (DisciplineDto disciplineDto) {
        ProfessorDto professorDto;

        try {
            professorDto = professorService.findById(disciplineDto.getProfessorId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("professor_id não foi encontrado");
        }

        return professorDto;
    }

    public static DisciplineModel CONVERT_DTO_TO_MODEL(DisciplineDto disciplineDto) {
        DisciplineModel disciplineModel = new DisciplineModel();
        disciplineModel.setId(disciplineDto.getId() != null ? disciplineDto.getId() : null);
        disciplineModel.setName(disciplineDto.getName());
        disciplineModel.setCode(disciplineDto.getCode());
        disciplineModel.setCourseModel(disciplineDto.getCourseDto() != null ? CourseService.CONVERT_DTO_TO_MODEL(disciplineDto.getCourseDto()) : null);
        disciplineModel.setProfessorModel(disciplineDto.getProfessorDto() != null ? ProfessorService.COVERT_DTO_TO_MODEL(disciplineDto.getProfessorDto()) : null);

        return disciplineModel;
    }

    public static DisciplineDto CONVERT_MODEL_TO_DTO(DisciplineModel disciplineModel) {
        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setId(disciplineModel.getId() != null ? disciplineModel.getId() : null);
        disciplineDto.setName(disciplineModel.getName());
        disciplineDto.setCode(disciplineModel.getCode());
        disciplineDto.setCourseId(disciplineModel.getCourseModel().getId() != null ? disciplineModel.getCourseModel().getId() : null);
        disciplineDto.setProfessorId(disciplineModel.getProfessorModel().getId() != null ? disciplineModel.getProfessorModel().getId() : null);
        disciplineDto.setCourseDto(disciplineModel.getCourseModel() != null ? CourseService.CONVERT_MODEL_TO_DTO(disciplineModel.getCourseModel()) : null);
        disciplineDto.setProfessorDto(disciplineModel.getProfessorModel() != null ? ProfessorService.COVERT_MODEL_TO_DTO(disciplineModel.getProfessorModel()) : null);

        return disciplineDto;
    }

}
