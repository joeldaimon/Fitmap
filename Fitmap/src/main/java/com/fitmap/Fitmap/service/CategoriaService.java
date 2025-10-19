package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.model.Categoria;
import com.fitmap.Fitmap.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public Categoria findByNombre(String nombre) {
        return repo.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + nombre));
    }

    public List<Categoria> getAll() {
        return repo.findAll();
    }

    public Categoria getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException (("Ejercicio no encontrado")));
    }

    public Categoria create(Categoria categoria) {
        return repo.save(categoria);
    }

    public Categoria update(Long id, Categoria updated) {
        Categoria categoria = getById(id);
        categoria.setNombre(updated.getNombre());
        return repo.save(categoria);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
