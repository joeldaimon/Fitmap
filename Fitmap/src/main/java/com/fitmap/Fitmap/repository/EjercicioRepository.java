package com.fitmap.Fitmap.repository;

import com.fitmap.Fitmap.model.Categoria;
import com.fitmap.Fitmap.model.Ejercicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    Page<Ejercicio> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    Page<Ejercicio> findByCategoriaNombreIgnoreCase(String categoria, Pageable pageable);

    Page<Ejercicio> findByNombreContainingIgnoreCaseAndCategoriaNombreIgnoreCase(
            String nombre, String categoria, Pageable pageable);

    List<Ejercicio> findByCategoria(Categoria categoria);

    Optional<Ejercicio> findByNombre(String nombre);
}
