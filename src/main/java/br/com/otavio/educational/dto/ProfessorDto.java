package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public class ProfessorDto {

    private Integer id;

    @JsonProperty("nome")
    @NotBlank(message = "campo nome não pode ser nulo")
    private String name;

    @NotBlank(message = "campo email não pode ser nulo")
    @Email(message = "o campo precisa ser preenchido com um email")
    private String email;

    @JsonProperty("telefone")
    @NotBlank(message = "campo telefone não pode ser nulo")
    private String phone;

    @JsonProperty("especialidade")
    @NotBlank(message = "campo especialidade não pode ser nulo")
    private String specialty;

    public ProfessorDto() {

    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
