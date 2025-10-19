package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.model.Role;
import com.fitmap.Fitmap.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository repo;

    RoleService(RoleRepository repo) {
        this.repo = repo;
    }

    public Role findByNombre(String nombre) {
        return repo.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombre));
    }

    public List<Role> getAll() {
        return repo.findAll();
    }

    public Role getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException (("Rol no encontrado")));
    }

    public Role create(Role role) {
        return repo.save(role);
    }

    public Role update(Long id, Role updated) {
        Role role = getById(id);
        role.setNombre(updated.getNombre());
        return repo.save(role);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
