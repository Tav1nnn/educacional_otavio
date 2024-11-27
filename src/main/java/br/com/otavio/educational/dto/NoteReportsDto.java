package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

@JsonPropertyOrder({"id", "note", "launch_date"})
public class NoteReportsDto {

    private Integer id;

    @NotNull(message = "O campo nota não pode ser nulo")
    @PositiveOrZero(message = "O campo nota precisa ser positivo")
    private Double note;

    @JsonProperty("data_lancamento")
    @NotNull(message = "campo data_lancamento não pode ser nulo")
    @PastOrPresent(message = "data_lancamento não pode ser uma data futura")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date launch_date;

    @JsonProperty("disciplina")
    private DisciplineReportsDto disciplineDto;

    public NoteReportsDto() {
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

    public void setDisciplineReportsDto(DisciplineReportsDto disciplineDto) {
        this.disciplineDto = disciplineDto;
    }
}
