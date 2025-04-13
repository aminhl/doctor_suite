package org.nexthope.doctorsuite.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nexthope.doctorsuite.commons.ApiResponse;
import org.nexthope.doctorsuite.keycloak.KeycloakUserDto;
import org.nexthope.doctorsuite.user.UserDTO;
import org.nexthope.doctorsuite.user.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.nexthope.doctorsuite.authentication.AuthenticationConstants.AUTHENTICATION_ENDPOINT;
import static org.nexthope.doctorsuite.authentication.AuthenticationConstants.REGISTER_ENDPOINT;
import static org.nexthope.doctorsuite.commons.ApplicationConstants.API_BASE_PATH;

@RestController
@RequestMapping(API_BASE_PATH + AUTHENTICATION_ENDPOINT)
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final UserMapper userMapper;

    @PostMapping(REGISTER_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDTO> registerUser(@RequestBody @Valid KeycloakUserDto keycloakUserDto) {
        authenticationService.registerUser(keycloakUserDto);
        return new ApiResponse<>(
                true,
                "User registered successfully",
                LocalDateTime.now(),
                userMapper.toUserDTO(keycloakUserDto));
    }

}
