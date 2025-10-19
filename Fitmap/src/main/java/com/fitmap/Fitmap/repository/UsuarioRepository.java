package com.fitmap.Fitmap.repository;

import com.fitmap.Fitmap.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);
}

