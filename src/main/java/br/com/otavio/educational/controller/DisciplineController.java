package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.DisciplineDto;
import br.com.otavio.educational.service.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/disciplinas")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @PostMapping
    public ResponseEntity<DisciplineDto> createDiscipline(@RequestBody @Valid DisciplineDto disciplineDto) {
        disciplineService.createDiscipline(disciplineDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<DisciplineDto>> findAllDisciplines() {
        Set<DisciplineDto> disciplines = disciplineService.findAll();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDto> findDisciplineById(@PathVariable Integer id) {
        DisciplineDto discipline = disciplineService.findById(id);
        return ResponseEntity.ok(discipline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDto> updateDiscipline(@PathVariable Integer id, @RequestBody @Valid DisciplineDto disciplineDto) {
        disciplineService.updateDiscipline(disciplineDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Integer id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}
