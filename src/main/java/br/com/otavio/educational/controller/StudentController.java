package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.StudentDto;
import br.com.otavio.educational.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/alunos")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE - Criar um novo estudante
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentDto studentDto) {
        studentService.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
    }

    // READ - Listar todos os estudantes
    @GetMapping
    public ResponseEntity<List<StudentDto>> findAllStudents() {
        List<StudentDto> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    // READ - Buscar um estudante pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable Integer id) {
        StudentDto student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    // UPDATE - Atualizar um estudante
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Integer id, @RequestBody @Valid StudentDto studentDto) {
        studentService.updateStudent(studentDto, id);
        return ResponseEntity.ok(studentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}