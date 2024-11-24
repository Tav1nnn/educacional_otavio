package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.DisciplineDto;
import br.com.otavio.educational.dto.NoteDto;
import br.com.otavio.educational.dto.MatriculationDto;
import br.com.otavio.educational.model.NoteModel;
import br.com.otavio.educational.repository.NoteRepository;
import br.com.otavio.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final MatriculationService matriculationService;
    private final DisciplineService disciplineService;

    public NoteService(NoteRepository noteRepository, MatriculationService matriculationService, DisciplineService disciplineService) {
        this.noteRepository = noteRepository;
        this.matriculationService = matriculationService;
        this.disciplineService = disciplineService;
    }

    public void createdNote (NoteDto noteDto) {
        MatriculationDto matriculationDto = getMatriculation(noteDto);
        DisciplineDto disciplineDto = getDiscipline(noteDto);

        noteDto.setMatriculationDto(matriculationDto);
        noteDto.setDisciplineDto(disciplineDto);

        NoteModel noteModel = CONVERT_DTO_TO_MODEL(noteDto);

        noteRepository.save(noteModel);
    }

    public Set<NoteDto> findAllMatrilations () {
        List<NoteModel> noteModelList = noteRepository.findAll();
        Set<NoteDto> noteDtoSet = new HashSet<>();

        for(NoteModel noteModel : noteModelList) {
            noteDtoSet.add(CONVERT_MODEL_TO_DTO(noteModel));
        }

        return noteDtoSet;
    }

    public NoteDto findById (Integer id) {
        NoteModel noteModel = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));

        return CONVERT_MODEL_TO_DTO(noteModel);
    }

    public void updateNote (Integer id, NoteDto noteDto) {
        NoteDto noteDtoOld = findById(id);
        MatriculationDto matriculationDto = getMatriculation(noteDto);
        DisciplineDto disciplineDto = getDiscipline(noteDto);

        noteDto.setMatriculationDto(matriculationDto);
        noteDto.setDisciplineDto(disciplineDto);
        noteDto.setId(id);

        noteRepository.save(CONVERT_DTO_TO_MODEL(noteDto));

    }

    public void delete (Integer id) {
        NoteDto noteDto = findById(id);
        noteRepository.delete(CONVERT_DTO_TO_MODEL(noteDto));
    }

    private MatriculationDto getMatriculation (NoteDto noteDto) {
        MatriculationDto matriculationDto;
        try {
            matriculationDto = matriculationService.findById(noteDto.getMatriculationId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return matriculationDto;
    }

    private DisciplineDto getDiscipline(NoteDto noteDto) {
        DisciplineDto disciplineDto;
        try {
            disciplineDto = disciplineService.findById(noteDto.getDisciplineId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return disciplineDto;
    }

    public static NoteModel CONVERT_DTO_TO_MODEL(NoteDto noteDto) {
        NoteModel noteModel = new NoteModel();
        noteModel.setId(noteDto.getId() != null ? noteDto.getId() : null);
        noteModel.setNote(noteDto.getNote());
        noteModel.setLaunch_date(noteDto.getLaunch_date());
        noteModel.setMatriculationModel(noteDto.getMatriculationDto() != null ? MatriculationService.CONVERT_DTO_TO_MODEL(noteDto.getMatriculationDto()) : null);
        noteModel.setDisciplineModel(noteDto.getDisciplineDto() != null ? DisciplineService.CONVERT_DTO_TO_MODEL(noteDto.getDisciplineDto()) : null);

        return noteModel;
    }

    private NoteDto CONVERT_MODEL_TO_DTO(NoteModel noteModel) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(noteModel.getId() != null ? noteModel.getId() : null);
        noteDto.setNote(noteModel.getNote());
        noteDto.setLaunch_date(noteModel.getLaunch_date());
        noteDto.setMatriculationId(noteModel.getMatriculationModel().getId() != null ? noteModel.getMatriculationModel().getId() : null);
        noteDto.setDisciplineId(noteModel.getDisciplineModel().getId() != null ? noteModel.getDisciplineModel().getId() : null);
        noteDto.setMatriculationDto(noteModel.getMatriculationModel() != null ? MatriculationService.CONVERT_MODEL_TO_DTO(noteModel.getMatriculationModel()) : null);
        noteDto.setDisciplineDto(noteModel.getDisciplineModel() != null ? DisciplineService.CONVERT_MODEL_TO_DTO(noteModel.getDisciplineModel()) : null);

        return noteDto;
    }
}
