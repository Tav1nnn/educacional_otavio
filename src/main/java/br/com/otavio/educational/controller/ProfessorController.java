package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.ProfessorDto;
import br.com.otavio.educational.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> createProfessor(@RequestBody @Valid ProfessorDto professorDto) {
        professorService.createProfessor(professorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> findAllProfessors() {
        List<ProfessorDto> students = professorService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> findProfessorById(@PathVariable Integer id) {
        ProfessorDto student = professorService.findById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> updateProfessor(@PathVariable Integer id, @RequestBody @Valid ProfessorDto professorDto) {
        professorService.updateProfessor(professorDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Integer id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
