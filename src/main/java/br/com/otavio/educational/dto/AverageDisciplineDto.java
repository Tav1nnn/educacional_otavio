package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"disciplineDto", "averageNotes"})
public class AverageDisciplineDto {

    @JsonProperty(value = "Diciplina")
    private DisciplineDto disciplineDto;

    @JsonProperty(value = "media_notas")
    private Double averageNotes;

    public AverageDisciplineDto(DisciplineDto disciplineDto, Double averageNotes) {
        this.disciplineDto = disciplineDto;
        this.averageNotes = averageNotes;
    }

}
