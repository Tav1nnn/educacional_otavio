package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.ClassDto;
import br.com.otavio.educational.service.ClassService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/turmas")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    public ResponseEntity<ClassDto> createClass(@RequestBody @Valid ClassDto classDto) {
        classService.createClass(classDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<ClassDto>> findAllClasss(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester,
            @RequestParam(name = "curso_id", required = false) Integer courseId
    ) {
        Set<ClassDto> classDto = classService.searchClass(year, semester, courseId);
        return ResponseEntity.ok(classDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDto> findClassById(@PathVariable Integer id) {
        ClassDto classDto = classService.findById(id);
        return ResponseEntity.ok(classDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDto> updateClass(@PathVariable Integer id, @RequestBody @Valid ClassDto classDto) {
        classService.updateClass(classDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
