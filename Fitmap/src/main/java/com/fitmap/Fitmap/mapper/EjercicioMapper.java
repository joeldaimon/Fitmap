package com.fitmap.Fitmap.mapper;

import com.fitmap.Fitmap.dto.EjercicioDTO;
import com.fitmap.Fitmap.model.Categoria;
import com.fitmap.Fitmap.model.Ejercicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EjercicioMapper {

    @Mapping(target = "categoria", source = "categoria", qualifiedByName = "categoriaToString")
    EjercicioDTO toDTO(Ejercicio ejercicio);

    @Mapping(target = "categoria", ignore = true)
    Ejercicio toEntity(EjercicioDTO dto);

    @Named("categoriaToString")
    default String MapCategoria(Categoria categoria) {
        if (categoria == null) return null;
        return categoria.getNombre();
    }
}
