package com.fitmap.Fitmap.controller;

import com.fitmap.Fitmap.model.Role;
import com.fitmap.Fitmap.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Endpoints de roles")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> getAll() {
        return service.getAll();
    }

    // GET /api/ejercicios/buscar?nombre=admin
    @GetMapping("/buscar")
    public ResponseEntity<Role> findByNombre(@RequestParam String nombre) {
       Role role = service.findByNombre(nombre);
        return ResponseEntity.ok(role);
    }

    // GET /api/roles/1
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) {
        Role role = service.getById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        Role saved = service.create(role);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role role) {
        Role updated = service.update(id, role);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
