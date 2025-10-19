package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.dto.RegistroProgresoDTO;
import com.fitmap.Fitmap.mapper.RegistroProgresoMapper;
import com.fitmap.Fitmap.model.Ejercicio;
import com.fitmap.Fitmap.model.RegistroProgreso;
import com.fitmap.Fitmap.model.Usuario;
import com.fitmap.Fitmap.repository.EjercicioRepository;
import com.fitmap.Fitmap.repository.RegistroProgresoRepository;
import com.fitmap.Fitmap.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistroProgresoServiceTest {

    @Mock
    private RegistroProgresoRepository repo;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EjercicioRepository ejercicioRepository;

    @Mock
    private RegistroProgresoMapper mapper;

    @InjectMocks
    private RegistroProgresoService service;

    private Usuario usuario;
    private Ejercicio ejercicio;
    private RegistroProgresoDTO dto;
    private RegistroProgreso entity;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Paco");

        ejercicio = new Ejercicio();
        ejercicio.setId(2L);
        ejercicio.setNombre("Sentadillas");

        dto = new RegistroProgresoDTO();
        dto.setUsuarioId(1L);
        dto.setEjercicioId(2L);
        dto.setRepeticiones(10);
        dto.setPeso(50.0);
        dto.setFecha(LocalDate.now());

        entity = new RegistroProgreso();
        entity.setId(99L);
        entity.setUsuario(usuario);
        entity.setEjercicio(ejercicio);
        entity.setRepeticiones(10);
        entity.setPeso(50.0);
        entity.setFecha(dto.getFecha());
    }

    @Test
    void create_shouldSaveAndReturnDto() {
        // given
        Mockito.when(mapper.toEntity(dto)).thenReturn(entity);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(ejercicioRepository.findById(2L)).thenReturn(Optional.of(ejercicio));
        Mockito.when(repo.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toDTO(entity)).thenReturn(dto);

        // when
        RegistroProgresoDTO result = service.create(dto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getUsuarioId());
        assertEquals(2L, result.getEjercicioId());
        assertEquals(10, result.getRepeticiones());

        Mockito.verify(repo, Mockito.times(1)).save(entity);
    }

    @Test
    void create_shouldThrowWhenUsuarioNotFound() {
        Mockito.when(mapper.toEntity(dto)).thenReturn(entity);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.create(dto));

        assertEquals("Usuario no encontrado con id 1", ex.getMessage());
    }

    @Test
    void create_shouldThrowWhenEjercicioNotFound() {
        Mockito.when(mapper.toEntity(dto)).thenReturn(entity);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(ejercicioRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.create(dto));

        assertEquals("Ejercicio no encontrado con id 2", ex.getMessage());
    }
}
