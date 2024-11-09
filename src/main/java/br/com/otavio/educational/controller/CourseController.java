package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.CourseDto;
import br.com.otavio.educational.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cursos")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody @Valid CourseDto professorDto) {
        courseService.createCourse(professorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> findAllCourses() {
        List<CourseDto> students = courseService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findCourseById(@PathVariable Integer id) {
        CourseDto student = courseService.findById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Integer id, @RequestBody @Valid CourseDto professorDto) {
        courseService.updateCourse(professorDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
