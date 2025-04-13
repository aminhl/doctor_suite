package org.nexthope.doctorsuite.user;

import org.mapstruct.Mapper;
import org.nexthope.doctorsuite.keycloak.KeycloakUserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(KeycloakUserDto dto);

}
