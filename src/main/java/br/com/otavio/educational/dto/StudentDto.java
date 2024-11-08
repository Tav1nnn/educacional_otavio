package br.com.otavio.educational.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

public class StudentDto {

    @NotBlank(message = "name field cannot be null")
    private String name;
    @NotBlank(message = "email field cannot be null")
    @Email(message = "this field needs to be an email")
    private String email;
    @NotBlank(message = "registration field cannot be null")
    private String registration;
    @NotNull(message = "dateBirth field cannot be null")
    @PastOrPresent(message = "the date cannot be in the future")
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
