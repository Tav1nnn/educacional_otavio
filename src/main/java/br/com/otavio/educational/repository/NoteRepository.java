package br.com.otavio.educational.repository;

import br.com.otavio.educational.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteModel, Integer> {
    @Query("SELECT n FROM notas n WHERE n.matriculationModel.studentModel.id = :studentId")
    Optional<List<NoteModel>> findByStudentId(@Param("studentId") Integer studentId);

    @Query("SELECT n FROM notas n WHERE n.matriculationModel.classModel.id = :classId")
    Optional<List<NoteModel>> findByClassId(@Param("classId") Integer classId);

    @Query("SELECT n FROM notas n WHERE n.disciplineModel.id = :disciplineId")
    Optional<List<NoteModel>> findByDisciplineId(@Param("disciplineId") Integer disciplineId);
}


