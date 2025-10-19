package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.dto.EjercicioDTO;
import com.fitmap.Fitmap.mapper.EjercicioMapper;
import com.fitmap.Fitmap.model.Categoria;
import com.fitmap.Fitmap.model.Ejercicio;
import com.fitmap.Fitmap.repository.CategoriaRepository;
import com.fitmap.Fitmap.repository.EjercicioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService {
    private final EjercicioRepository repo;
    private final CategoriaRepository categoriaRepository;
    private final EjercicioMapper mapper;

    public EjercicioService(EjercicioRepository repo,
                            CategoriaRepository categoriaRepository,
                            EjercicioMapper mapper) {
        this.repo = repo;
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    /*public List<EjercicioDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }*/

    public Page<EjercicioDTO> getAll(
            String nombre, String categoria, int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Ejercicio> ejerciciosPage;

        if (nombre != null && !nombre.isEmpty() && categoria != null && !categoria.isEmpty()) {
            ejerciciosPage = repo.findByNombreContainingIgnoreCaseAndCategoriaNombreIgnoreCase(nombre, categoria, pageable);
        } else if (nombre != null && !nombre.isEmpty()) {
            ejerciciosPage = repo.findByNombreContainingIgnoreCase(nombre, pageable);
        } else if (categoria != null && !categoria.isEmpty()) {
            ejerciciosPage = repo.findByCategoriaNombreIgnoreCase(categoria, pageable);
        } else {
            ejerciciosPage = repo.findAll(pageable);
        }

        return ejerciciosPage.map(mapper::toDTO);
    }

    public EjercicioDTO getById(Long id) {
        Ejercicio rp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException (("Ejercicio no encontrado")));

        return mapper.toDTO(rp);
    }

    public EjercicioDTO create(EjercicioDTO dto) {
        Ejercicio ejercicio = mapper.toEntity(dto);

        Categoria categoria = categoriaRepository.findByNombre(dto.getCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con nombre " + dto.getCategoria()));

        ejercicio.setNombre(dto.getNombre());
        ejercicio.setCategoria(categoria);
        ejercicio.setDificultad(dto.getDificultad());
        return mapper.toDTO(repo.save(ejercicio));
    }

    public EjercicioDTO update(Long id, EjercicioDTO dto) {
        Ejercicio ejercicio = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con id " + id));


        Categoria categoria = categoriaRepository.findByNombre(dto.getCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + dto.getCategoria()));

        ejercicio.setNombre(dto.getNombre());
        ejercicio.setCategoria(categoria);
        ejercicio.setDificultad(dto.getDificultad());
        return mapper.toDTO(repo.save(ejercicio));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Page<Ejercicio> buscarPorNombre(String nombre, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return repo.findByNombreContainingIgnoreCase(nombre, pageable);
    }

    public List<EjercicioDTO> findByCategoria(Categoria categoria) {
        return repo.findByCategoria(categoria).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public EjercicioDTO findByNombre(String nombre) {
        Ejercicio ejercicio = repo.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado: " + nombre));

        return mapper.toDTO(ejercicio);
    }

}
