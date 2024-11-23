package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"id", "studentId", "classId"})
public class MatriculationDto {

    private Integer id;

    @JsonProperty(value = "aluno_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo aluno_id não pode ser nulo")
    private Integer studentId;

    @JsonProperty(value = "turma_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "o campo  turma_id não pode ser nulo")
    private Integer classId;

    @JsonProperty("aluno")
    private StudentDto studentDto;

    @JsonProperty("turma")
    private ClassDto classDto;

    public MatriculationDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    public ClassDto getClassDto() {
        return classDto;
    }

    public void setClassDto(ClassDto classDto) {
        this.classDto = classDto;
    }
}
