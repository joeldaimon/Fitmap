package com.fitmap.Fitmap.mapper;

import com.fitmap.Fitmap.dto.CategoriaDTO;
import com.fitmap.Fitmap.model.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO toDTO(Categoria categoria);

    Categoria toEntity(CategoriaDTO dto);

}
