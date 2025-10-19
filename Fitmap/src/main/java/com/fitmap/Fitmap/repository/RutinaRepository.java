package com.fitmap.Fitmap.repository;

import com.fitmap.Fitmap.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    Optional<Rutina> findByNombre(String nombre);
}
