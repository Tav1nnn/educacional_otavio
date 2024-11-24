package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

@JsonPropertyOrder({"id", "note", "launch_date", "matriculationId", "disciplineId"})
public class NoteDto {

    private Integer id;

    @NotNull(message = "O campo nota não pode ser nulo")
    @PositiveOrZero(message = "O campo nota precisa ser positivo")
    private Double note;

    @JsonProperty("data_lancamento")
    @NotNull(message = "campo data_lancamento não pode ser nulo")
    @PastOrPresent(message = "data_lancamento não pode ser uma data futura")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date launch_date;

    @JsonProperty(value = "matricula_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo matricula_id não pode ser nulo")
    private Integer matriculationId;

    @JsonProperty(value = "disciplina_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "o campo  disciplina_id não pode ser nulo")
    private Integer disciplineId;

    @JsonProperty("matricula")
    private MatriculationDto matriculationDto;

    @JsonProperty("disciplina")
    private DisciplineDto disciplineDto;

    public NoteDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public Integer getMatriculationId() {
        return matriculationId;
    }

    public void setMatriculationId(Integer matriculationId) {
        this.matriculationId = matriculationId;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public MatriculationDto getMatriculationDto() {
        return matriculationDto;
    }

    public void setMatriculationDto(MatriculationDto matriculationDto) {
        this.matriculationDto = matriculationDto;
    }

    public DisciplineDto getDisciplineDto() {
        return disciplineDto;
    }

    public void setDisciplineDto(DisciplineDto disciplineDto) {
        this.disciplineDto = disciplineDto;
    }
}
