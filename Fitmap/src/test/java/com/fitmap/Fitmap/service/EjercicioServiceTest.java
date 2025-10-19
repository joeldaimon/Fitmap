package com.fitmap.Fitmap.service;

import com.fitmap.Fitmap.dto.EjercicioDTO;
import com.fitmap.Fitmap.mapper.EjercicioMapper;
import com.fitmap.Fitmap.model.Ejercicio;
import com.fitmap.Fitmap.repository.CategoriaRepository;
import com.fitmap.Fitmap.repository.EjercicioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EjercicioServiceTest {

    @Mock
    private EjercicioRepository repo;

    @Mock
    private CategoriaRepository categoriaRepository;

    private EjercicioService service;

    @BeforeEach
    void setUp() {
        EjercicioMapper mapper = Mappers.getMapper(EjercicioMapper.class);
        service = new EjercicioService(repo, categoriaRepository, mapper);
    }

    @Test
    void testGetByIdFound() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(1L);
        ejercicio.setNombre("Sentadillas");

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(ejercicio));

        EjercicioDTO result = service.getById(1L);
        Assertions.assertEquals("Sentadillas", result.getNombre());
    }

    @Test
    void testGetByIdNotFound() {
        Mockito.when(repo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> service.getById(99L));

        Assertions.assertEquals("Ejercicio no encontrado", ex.getMessage());
    }

}
