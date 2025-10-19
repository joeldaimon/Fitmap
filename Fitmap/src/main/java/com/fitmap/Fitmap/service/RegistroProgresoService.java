package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.dto.EjercicioDTO;
import com.fitmap.Fitmap.dto.RegistroProgresoDTO;
import com.fitmap.Fitmap.mapper.RegistroProgresoMapper;
import com.fitmap.Fitmap.model.Ejercicio;
import com.fitmap.Fitmap.model.RegistroProgreso;
import com.fitmap.Fitmap.model.Usuario;
import com.fitmap.Fitmap.repository.EjercicioRepository;
import com.fitmap.Fitmap.repository.RegistroProgresoRepository;
import com.fitmap.Fitmap.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroProgresoService {
    private final RegistroProgresoRepository repo;
    private final UsuarioRepository usuarioRepository;
    private final EjercicioRepository ejercicioRepository;
    private final RegistroProgresoMapper mapper;

    public RegistroProgresoService(RegistroProgresoRepository registroProgresoRepository,
                                   UsuarioRepository usuarioRepository,
                                   EjercicioRepository ejercicioRepository,
                                   RegistroProgresoMapper mapper) {
        this.repo = registroProgresoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ejercicioRepository = ejercicioRepository;
        this.mapper = mapper; // si no usas Spring @Mapper
    }

    public List<RegistroProgresoDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public RegistroProgresoDTO  getById(Long id) {
        RegistroProgreso rp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException (("Registro no encontrado")));

        return mapper.toDTO(rp);
    }

    public RegistroProgresoDTO create(RegistroProgresoDTO dto) {
        RegistroProgreso rp = mapper.toEntity(dto);

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getUsuarioId()));

        Ejercicio ejercicio = ejercicioRepository.findById(dto.getEjercicioId())
                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con id " + dto.getEjercicioId()));

        rp.setUsuario(usuario);
        rp.setEjercicio(ejercicio);
        return mapper.toDTO(repo.save(rp));
    }

    public RegistroProgresoDTO update(Long id, RegistroProgresoDTO dto) {
        RegistroProgreso registro = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con id " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getUsuarioId()));
        Ejercicio ejercicio = ejercicioRepository.findById(dto.getEjercicioId())
                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con id " + dto.getEjercicioId()));


        registro.setUsuario(usuario);
        registro.setEjercicio(ejercicio);
        registro.setRepeticiones(dto.getRepeticiones());
        registro.setPeso(dto.getPeso());
        registro.setFecha(dto.getFecha());
        return mapper.toDTO(repo.save(registro));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Registro no encontrado con id " + id);
        }
        repo.deleteById(id);
    }

}
