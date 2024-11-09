package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseDto {

    @JsonProperty("nome")
    @NotBlank(message = "campo nome não pode ser nulo")
    private String name;

    @JsonProperty("codigo")
    @NotBlank(message = "campo codigo não pode ser nulo")
    private String code;

    @JsonProperty("carga_horaria")
    @NotNull(message = "campo carga_horaria não pode ser nulo")
    private Integer workload;

    public CourseDto() {

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

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }
}
