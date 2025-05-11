package com.example.user.AuthController;

import com.example.user.AuthController.dto.AuthenticationResponse;
import com.example.user.AuthController.dto.UserDTO;
import com.example.user.AuthService.AuthService;
import com.example.user.mapper.UserDTOMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserDTO register(@Valid @RequestBody UserDTO userDTO) {
        return authService.register(UserDTOMapper.INSTANCE.toUser(userDTO));
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@Valid @RequestBody UserDTO userDTO) {
        return authService.authenticate(UserDTOMapper.INSTANCE.toUser(userDTO));
    }
}
