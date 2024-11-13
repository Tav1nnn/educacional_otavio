package br.com.otavio.educational.repository;

import br.com.otavio.educational.model.DisciplineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplineRepository extends JpaRepository<DisciplineModel, Integer> {

    Optional<DisciplineModel> findByCode (String code);

}

