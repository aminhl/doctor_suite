package org.nexthope.doctorsuite.authentication;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.nexthope.doctorsuite.keycloak.KeycloakUserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("${keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;

    public void registerUser(KeycloakUserDto keycloakUserDto) {
        log.info("Starting registration for user: {}", keycloakUserDto.username());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(keycloakUserDto.username());
        user.setEmail(keycloakUserDto.email());
        user.setFirstName(keycloakUserDto.firstname());
        user.setLastName(keycloakUserDto.lastname());
        user.setEnabled(true);

        Response keycloakResponse = keycloak.realm(realm).users().create(user);
        int keycloakResponseStatus = keycloakResponse.getStatus();

        if (keycloakResponseStatus != HttpStatus.SC_CREATED) {
            log.error("Failed to create user {}. HTTP status: {}", keycloakUserDto.username(), keycloakResponseStatus);
            throw new RuntimeException("Error creating user: HTTP status " + keycloakResponseStatus);
        }

        String createdUserId = CreatedResponseUtil.getCreatedId(keycloakResponse);
        log.info("User {} created successfully", keycloakUserDto.username());

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(keycloakUserDto.password());

        keycloak.realm(realm).users().get(createdUserId).resetPassword(credential);
        log.info("Password set successfully for user: {}", keycloakUserDto.username());
    }

}
