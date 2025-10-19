package com.fitmap.Fitmap.controller;

import com.fitmap.Fitmap.model.Categoria;
import com.fitmap.Fitmap.model.Role;
import com.fitmap.Fitmap.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "Endpoints de categorías")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> getAll() {
        return service.getAll();
    }

    // GET /api/ejercicios/buscar?nombre=admin
    @GetMapping("/buscar")
    public ResponseEntity<Categoria> findByNombre(@RequestParam String nombre) {
        Categoria cat = service.findByNombre(nombre);
        return ResponseEntity.ok(cat);
    }

    // GET /api/roles/1
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        Categoria cat = service.getById(id);
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria) {
        Categoria saved = service.create(categoria);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria updated = service.update(id, categoria);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
