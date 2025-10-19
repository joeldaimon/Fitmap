package com.fitmap.Fitmap.mapper;

import com.fitmap.Fitmap.dto.RoleDTO;
import com.fitmap.Fitmap.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role role);

    Role toEntity(RoleDTO dto);
}
