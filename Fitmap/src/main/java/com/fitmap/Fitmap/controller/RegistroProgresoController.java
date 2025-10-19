package com.fitmap.Fitmap.controller;

import com.fitmap.Fitmap.dto.RegistroProgresoDTO;
import com.fitmap.Fitmap.service.RegistroProgresoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresos")
@Tag(name = "Registro Progreso", description = "Endpoints de registros de progresos")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistroProgresoController {

    private final RegistroProgresoService service;

    public RegistroProgresoController(RegistroProgresoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RegistroProgresoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroProgresoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<RegistroProgresoDTO> create(@Valid @RequestBody RegistroProgresoDTO dto) {
        RegistroProgresoDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroProgresoDTO> update(@PathVariable Long id,
                                                      @RequestBody RegistroProgresoDTO dto) {
        RegistroProgresoDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

