package br.com.otavio.educational.repository;

import br.com.otavio.educational.model.CourseModel;
import br.com.otavio.educational.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseModel, Integer> {

}
