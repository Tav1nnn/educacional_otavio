package br.com.otavio.educational.repository;

import br.com.otavio.educational.model.ClassModel;
import br.com.otavio.educational.model.MatriculationModel;
import br.com.otavio.educational.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface MatriculationRepository extends JpaRepository<MatriculationModel, Integer> {
    Optional<MatriculationModel> findByStudentModelAndClassModel(StudentModel studentModel, ClassModel classModel);
}

