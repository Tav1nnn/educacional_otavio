package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.ProfessorDto;
import br.com.otavio.educational.model.ProfessorModel;
import br.com.otavio.educational.repository.ProfessorRepository;
import br.com.otavio.educational.service.exception.ResourceEmailAlreadyExistsException;
import br.com.otavio.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void createProfessor(ProfessorDto professorDto) {
        verifyEmail(professorDto);

        ProfessorModel professorModel = convertDtoToModel(professorDto);

        professorRepository.save(professorModel);
    }

    public void updateProfessor(ProfessorDto professorDto, Integer id) {
        ProfessorDto verifyProfessorDto = findById(id);

        if(!verifyProfessorDto.getEmail().equals(professorDto.getEmail())){
            verifyEmail(professorDto);
        }
        
        ProfessorModel professorModel = convertDtoToModel(professorDto);
        professorModel.setId(id);
        professorRepository.save(professorModel);
    }

    public ProfessorDto findById (Integer id) {
        ProfessorModel professorModel = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return convertModelToDto(professorModel);
    }

    public List<ProfessorDto> findAll () {
        List<ProfessorModel> lstProfessorModel = professorRepository.findAll();
        List<ProfessorDto> lstProfessorDto = new ArrayList<>();

        for(ProfessorModel professorModel : lstProfessorModel) {
            lstProfessorDto.add(convertModelToDto(professorModel));
        }

        return lstProfessorDto;
    }

    public void deleteProfessor(Integer id) {
        ProfessorDto verifyProfessorDto = findById(id);
        ProfessorModel professorModel = convertDtoToModel(verifyProfessorDto);
        professorModel.setId(id);
        professorRepository.delete(professorModel);
    }

    private void verifyEmail (ProfessorDto professorDto) {
        Optional<ProfessorModel> optionalProfessorModel = professorRepository.findByEmail(professorDto.getEmail());

        if(optionalProfessorModel.isPresent()){
            throw new ResourceEmailAlreadyExistsException("Já existe um aluno com teste email");
        }
    }

    private ProfessorModel convertDtoToModel(ProfessorDto professorDto) {
        ProfessorModel professorModel = new ProfessorModel();
        professorModel.setId(professorDto.getId() != null ? professorDto.getId() : null);
        professorModel.setName(professorDto.getName());
        professorModel.setEmail(professorDto.getEmail());
        professorModel.setPhone(professorDto.getPhone());
        professorModel.setSpecialty(professorDto.getSpecialty());

        return professorModel;
    }

    private ProfessorDto convertModelToDto(ProfessorModel professorModel) {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setId(professorModel.getId() != null ? professorModel.getId() : null);
        professorDto.setName(professorModel.getName());
        professorDto.setEmail(professorModel.getEmail());
        professorDto.setPhone(professorModel.getPhone());
        professorDto.setSpecialty(professorModel.getSpecialty());

        return professorDto;
    }

}
