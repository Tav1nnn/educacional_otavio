package br.com.otavio.educational.controller;

import br.com.otavio.educational.dto.NoteDto;
import br.com.otavio.educational.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/notas")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody @Valid NoteDto noteDto) {
        noteService.createdNote(noteDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<NoteDto>> findAllNotes() {
        Set<NoteDto> notes = noteService.findAllMatrilations();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> findNoteById(@PathVariable Integer id) {
        NoteDto note = noteService.findById(id);
        return ResponseEntity.ok(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Integer id, @RequestBody @Valid NoteDto noteDto) {
        noteService.updateNote(id, noteDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
