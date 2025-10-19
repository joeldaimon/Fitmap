package com.fitmap.Fitmap.mapper;

import com.fitmap.Fitmap.dto.RoleDTO;
import com.fitmap.Fitmap.dto.UsuarioDTO;
import com.fitmap.Fitmap.model.Role;
import com.fitmap.Fitmap.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UsuarioMapper {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "roleDTOtoRole")
    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO dto);

    @Named("roleDTOtoRole")
    default Set<RoleDTO> mapRoles(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream().map(r -> {
            RoleDTO dto = new RoleDTO();
            dto.setNombre(r.getNombre());
            return dto;
        }).collect(Collectors.toSet());
    }

}
