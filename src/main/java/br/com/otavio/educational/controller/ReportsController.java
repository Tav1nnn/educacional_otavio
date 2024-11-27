package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.AverageClassDto;
import br.com.otavio.educational.dto.AverageDisciplineDto;
import br.com.otavio.educational.dto.NoteReportsDto;
import br.com.otavio.educational.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/relatorios")
public class ReportsController {

    private final NoteService noteService;

    public ReportsController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(value = "/notaPorAluno/{id}")
    public ResponseEntity<Set<NoteReportsDto>> notesByStudent(@PathVariable Integer id) {
        Set<NoteReportsDto> notes = noteService.getNotesByAlunoId(id);
        return ResponseEntity.ok(notes);
    }

    @GetMapping(value = "/mediaPorTurma/{id}")
    public ResponseEntity<AverageClassDto> averageDtoResponseEntity(@PathVariable Integer id) {
        AverageClassDto averageDto = noteService.averageNotesByClass(id);
        return ResponseEntity.ok(averageDto);
    }

    @GetMapping(value = "/mediaPorDisciplina/{id}")
    public ResponseEntity<AverageDisciplineDto> averageDisciplineDtoResponseEntity(@PathVariable Integer id) {
        AverageDisciplineDto averageDto = noteService.averageNotesByDiscipline(id);
        return ResponseEntity.ok(averageDto);
    }
}
