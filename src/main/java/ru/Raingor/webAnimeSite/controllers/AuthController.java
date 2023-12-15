package ru.Raingor.webAnimeSite.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.Raingor.webAnimeSite.payload.request.LoginRequest;
import ru.Raingor.webAnimeSite.payload.request.SignUpRequest;
import ru.Raingor.webAnimeSite.security.services.AuthService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest
            , BindingResult bindingResult) {
        return authService.authUser(loginRequest, bindingResult);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest
            , BindingResult bindingResult) {
        return authService.regUser(signUpRequest, bindingResult);
    }

}
