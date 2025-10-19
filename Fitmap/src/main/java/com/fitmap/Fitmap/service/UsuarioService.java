package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.model.Role;
import com.fitmap.Fitmap.model.Usuario;
import com.fitmap.Fitmap.repository.RoleRepository;
import com.fitmap.Fitmap.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    private final RoleRepository roleRepository;

    public UsuarioService(UsuarioRepository repo, RoleRepository roleRepository) {
        this.repo = repo;
        this.roleRepository = roleRepository;
    }

    public Usuario findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + id));
    }

    public Usuario findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + email));
    }

    public List<Usuario> getAll() {
        return repo.findAll();
    }

    public Usuario create(Usuario dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        Set<Role> roles = dto.getRoles().stream()
                .map(rdto -> roleRepository.findByNombre(rdto.getNombre())
                        .orElseThrow(() -> new RuntimeException("Role no encontrado: " + rdto.getNombre()))
                )
                .collect(Collectors.toSet());

        usuario.setRoles(roles);
        return repo.save(usuario);
    }

    public Usuario update(Long id, Usuario updated) {
        Usuario usuario = findById(id);
        usuario.setNombre(updated.getNombre());
        Set<Role> roles = updated.getRoles().stream()
                .map(rdto -> roleRepository.findByNombre(rdto.getNombre())
                        .orElseThrow(() -> new RuntimeException("Role no encontrado: " + rdto.getNombre()))
                )
                .collect(Collectors.toSet());

        usuario.setRoles(roles);
        return repo.save(usuario);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
