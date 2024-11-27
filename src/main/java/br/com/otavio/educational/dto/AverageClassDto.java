package br.com.otavio.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"classDto", "averageNotes"})
public class AverageClassDto {

    @JsonProperty(value = "Turma")
    private ClassDto classDto;

    @JsonProperty(value = "media_notas")
    private Double averageNotes;

    public AverageClassDto(ClassDto classDto, Double averageNotes) {
        this.classDto = classDto;
        this.averageNotes = averageNotes;
    }
}
