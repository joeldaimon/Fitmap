package com.fitmap.Fitmap.controller;

import com.fitmap.Fitmap.model.Usuario;
import com.fitmap.Fitmap.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Endpoints de usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok (service.getAll ());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok (service.findById (id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok (service.findByEmail (email));
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario) {
        Usuario saved = service.create(usuario);
        return ResponseEntity.status (HttpStatus.CREATED).body (saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id,
                                          @RequestBody Usuario usuario) {
        Usuario updated = service.update (id, usuario);
        return ResponseEntity.ok (updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete (id);
        return ResponseEntity.noContent ().build ();
    }
}
