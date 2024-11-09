package br.com.otavio.educational.repository;

import br.com.otavio.educational.model.ProfessorModel;
import br.com.otavio.educational.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> {

    Optional<ProfessorModel> findByEmail(String email);
}
