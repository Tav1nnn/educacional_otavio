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
    public ResponseEntity<ProfessorDto> createStudent(@RequestBody @Valid ProfessorDto professorDto) {
        professorService.createStudent(professorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> findAllStudents() {
        List<ProfessorDto> students = professorService.findAll();
        return ResponseEntity.ok(students);
    }

    // READ - Buscar um estudante pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> findStudentById(@PathVariable Integer id) {
        ProfessorDto student = professorService.findById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> updateStudent(@PathVariable Integer id, @RequestBody @Valid ProfessorDto professorDto) {
        professorService.updateStudent(professorDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        professorService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
