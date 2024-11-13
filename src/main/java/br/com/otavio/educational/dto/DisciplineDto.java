package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"id", "name", "code", "courseDto", "professorDto"})
public class DisciplineDto {

    private Integer id;

    @JsonProperty("nome")
    @NotBlank(message = "campo nome não pode ser nulo")
    private String name;

    @JsonProperty("codigo")
    @NotBlank(message = "campo codigo não pode ser nulo")
    private String code;

    @JsonProperty(value = "curso_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo curso_id não pode ser nulo")
    private Integer courseId;

    @JsonProperty(value = "professor_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo professor_id não pode ser nulo")
    private Integer professorId;

    @JsonProperty("curso")
    private CourseDto courseDto;

    @JsonProperty("professor")
    private ProfessorDto professorDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public ProfessorDto getProfessorDto() {
        return professorDto;
    }

    public void setProfessorDto(ProfessorDto professorDto) {
        this.professorDto = professorDto;
    }
}
