package com.fitmap.Fitmap.repository;

import com.fitmap.Fitmap.model.RegistroProgreso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistroProgresoRepository extends JpaRepository<RegistroProgreso, Long> {
    Optional<RegistroProgreso> findById(Long id);
}
