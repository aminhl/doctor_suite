package org.nexthope.doctorsuite.keycloak;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record KeycloakUserDto(
        @NotBlank String username,
        @Email @NotBlank String email,
        @NotBlank String firstname,
        @NotBlank String lastname,
        @NotBlank String password
) { }