package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;

import java.util.Date;

public class StudentDto {

    @JsonProperty("nome")
    @NotBlank(message = "campo nome não pode ser nulo")
    private String name;

    @NotBlank(message = "campo email não pode ser nulo")
    @Email(message = "o campo precisa ser preenchido com um email")
    private String email;

    @JsonProperty("matricula")
    @NotBlank(message = "campo matricula não pode ser nula")
    private String registration;

    @JsonProperty("data_nascimento")
    @NotNull(message = "campo data_nascimento não pode ser nulo")
    @PastOrPresent(message = "data_nascimento não pode ser uma data futura")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateBirth;

    public StudentDto() {

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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }
}
