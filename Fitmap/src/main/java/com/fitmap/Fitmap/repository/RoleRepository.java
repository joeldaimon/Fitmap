package com.fitmap.Fitmap.repository;

import com.fitmap.Fitmap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombre(String nombre);
}
