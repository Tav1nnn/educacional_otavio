package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.MatriculationDto;
import br.com.otavio.educational.service.MatriculationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/matriculas")
public class MatriculationController {

    private final MatriculationService matriculationService;

    public MatriculationController(MatriculationService matriculationService) {
        this.matriculationService = matriculationService;
    }

    @PostMapping
    public ResponseEntity<MatriculationDto> createMatriculation(@RequestBody @Valid MatriculationDto matriculationDto) {
        matriculationService.createdMatriculation(matriculationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<MatriculationDto>> findAllMatriculations() {
        Set<MatriculationDto> matriculations = matriculationService.findAllMatrilations();
        return ResponseEntity.ok(matriculations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculationDto> findMatriculationById(@PathVariable Integer id) {
        MatriculationDto matriculation = matriculationService.findById(id);
        return ResponseEntity.ok(matriculation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculationDto> updateMatriculation(@PathVariable Integer id, @RequestBody @Valid MatriculationDto matriculationDto) {
        matriculationService.updateMatriculation(id, matriculationDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatriculation(@PathVariable Integer id) {
        matriculationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
