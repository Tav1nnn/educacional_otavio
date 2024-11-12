package br.com.otavio.educational.repository;

import br.com.otavio.educational.model.ClassModel;
import br.com.otavio.educational.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ClassRepository extends JpaRepository<ClassModel, Integer> {
        @Query("SELECT c FROM turmas c " +
                "WHERE (:year IS NULL OR c.year = :year) " +
                "AND (:semester IS NULL OR c.semester = :semester) " +
                "AND (:courseId IS NULL OR c.courseModel.id = :courseId)")
        Set<ClassModel> searchClasses(
                @Param("year") Integer year,
                @Param("semester") Integer semester,
                @Param("courseId") Integer courseId
        );
}

