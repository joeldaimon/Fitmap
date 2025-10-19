package com.fitmap.Fitmap.controller;

import com.fitmap.Fitmap.dto.RutinaDTO;
import com.fitmap.Fitmap.service.RutinaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@Tag(name = "Rutinas", description = "Endpoints de rutinas")
@CrossOrigin(origins = "http://localhost:5173")
public class RutinaController {
    private final RutinaService service;

    public RutinaController(RutinaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RutinaDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaDTO> getByNombre(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<RutinaDTO> create(@Valid @RequestBody RutinaDTO dto) {
        RutinaDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutinaDTO> update(@PathVariable Long id,
                                                      @RequestBody RutinaDTO dto) {
        RutinaDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
