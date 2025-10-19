package com.fitmap.Fitmap.controller;

import com.fitmap.Fitmap.dto.EjercicioDTO;
import com.fitmap.Fitmap.model.Categoria;
import com.fitmap.Fitmap.service.CategoriaService;
import com.fitmap.Fitmap.service.EjercicioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
@Tag(name = "Ejercicios", description = "Endpoints de ejercicios")
@CrossOrigin(origins = "http://localhost:5173")
public class EjercicioController {

    private final EjercicioService service;
    private final CategoriaService categoriaService;

    public EjercicioController(EjercicioService service, CategoriaService categoriaService) {
        this.service = service;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public Page<EjercicioDTO> getAll(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return service.getAll(nombre, categoria, page, size, sortBy, sortDir);
    }

    // GET /api/ejercicios/1
    @GetMapping("/{id}")
    public ResponseEntity<EjercicioDTO> getById(@PathVariable Long id) {
        EjercicioDTO ejercicio = service.getById(id);
        return ResponseEntity.ok(ejercicio);
    }

    // GET /api/ejercicios/buscar?categoria=Pierna
    @GetMapping("/buscar")
    public ResponseEntity<List<EjercicioDTO>> findByCategoria(@RequestParam String categoria) {
        Categoria cat = categoriaService.findByNombre(String.valueOf (categoria));
        List<EjercicioDTO> ejercicios = service.findByCategoria(cat);
        return ResponseEntity.ok(ejercicios);
    }

    // GET /api/ejercicios/nombre?nombre=Bulgaras
    @GetMapping("/nombre")
    public ResponseEntity<EjercicioDTO> findByNombre(@RequestParam String nombre) {
        EjercicioDTO ejercicio = service.findByNombre(nombre);
        return ResponseEntity.ok(ejercicio);
    }

    @PostMapping
    public ResponseEntity<EjercicioDTO> create(@Valid @RequestBody EjercicioDTO dto) {
        EjercicioDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EjercicioDTO> update(@PathVariable Long id, @RequestBody EjercicioDTO dto) {
        EjercicioDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
