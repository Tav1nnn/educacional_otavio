package br.com.otavio.educational.repository;

import br.com.otavio.educational.model.ClassModel;
import br.com.otavio.educational.model.MatriculationModel;
import br.com.otavio.educational.model.NoteModel;
import br.com.otavio.educational.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteModel, Integer> {

}

