package br.com.otavio.educational.dto;

import br.com.otavio.educational.model.CourseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@JsonPropertyOrder({"id", "year", "semester", "courseDto"})
public class ClassDto {

    private Integer id;

    @JsonProperty("ano")
    @NotNull(message = "campo ano não pode ser nulo")
    @Positive(message = "o campo ano só pode ser positivo")
    private Integer year;

    @JsonProperty("semestre")
    @Min(value = 1, message = "o campo semestre só pode ser 1 ou 2")
    @Max(value = 2, message = "o campo semestre só pode ser 1 ou 2")
    private Integer semester;

    @JsonProperty(value = "curso_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo curso_id não pode ser nulo")
    private Integer courseId;

    @JsonProperty("curso")
    private CourseDto courseDto;

    public ClassDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }
}
