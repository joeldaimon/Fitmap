package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.dto.EjercicioDTO;
import com.fitmap.Fitmap.dto.RutinaDTO;
import com.fitmap.Fitmap.mapper.RutinaMapper;
import com.fitmap.Fitmap.model.Ejercicio;
import com.fitmap.Fitmap.model.RegistroProgreso;
import com.fitmap.Fitmap.model.Rutina;
import com.fitmap.Fitmap.model.Usuario;
import com.fitmap.Fitmap.repository.EjercicioRepository;
import com.fitmap.Fitmap.repository.RutinaRepository;
import com.fitmap.Fitmap.repository.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RutinaService {

    private final RutinaRepository repo;
    private final UsuarioRepository usuarioRepository;
    private final EjercicioRepository ejercicioRepository;
    private final RutinaMapper mapper;

    public RutinaService(RutinaRepository repo,
                         UsuarioRepository usuarioRepository,
                         EjercicioRepository ejercicioRepository,
                         RutinaMapper mapper) {
        this.repo = repo;
        this.usuarioRepository = usuarioRepository;
        this.ejercicioRepository = ejercicioRepository;
        this.mapper = mapper;
    }

    public RutinaDTO findByNombre(String nombre) {
        Rutina rutina = repo.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + nombre));

        return mapper.toDTO(rutina);
    }

    public List<RutinaDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public RutinaDTO getById(Long id) {
        Rutina rutina = repo.findById(id)
                .orElseThrow(() -> new RuntimeException (("Rutina no encontrada")));

        return mapper.toDTO(rutina);
    }

    public RutinaDTO create(RutinaDTO dto) {
        Rutina rutina = mapper.toEntity(dto);

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getUsuarioId()));

        Set<Ejercicio> ejercicios = new HashSet<>();
        /*for(EjercicioDTO ex : dto.getEjercicios ()) {
            Ejercicio ejercicio = ejercicioRepository.findByNombre (ex.getNombre())
                    .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con nombre " + ex.getNombre()));
            ejercicios.add(ejercicio);
        }*/
        rutina.setUsuario(usuario);
        rutina.setEjercicios(ejercicios);
        return mapper.toDTO(repo.save(rutina));
    }

    public RutinaDTO update(Long id, RutinaDTO dto) {
        Rutina rutina = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada con id " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getUsuarioId()));

        Set<Ejercicio> ejercicios = dto.getEjercicios().stream()
                .map(ejercicio -> ejercicioRepository.findByNombre(ejercicio.getNombre())
                        .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado: " + ejercicio.getNombre())))
                .collect(Collectors.toSet());

        rutina.setEjercicios(ejercicios);
        rutina.setNombre(dto.getNombre());
        rutina.setUsuario(usuario);
        rutina.setEjercicios(ejercicios);
        return mapper.toDTO(repo.save(rutina));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Rutina no encontrada con id " + id);
        }
        repo.deleteById(id);
    }

}
