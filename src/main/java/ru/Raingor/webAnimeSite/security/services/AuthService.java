package ru.Raingor.webAnimeSite.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.Raingor.webAnimeSite.models.Role;
import ru.Raingor.webAnimeSite.models.User;
import ru.Raingor.webAnimeSite.payload.request.LoginRequest;
import ru.Raingor.webAnimeSite.payload.request.SignUpRequest;
import ru.Raingor.webAnimeSite.payload.response.JwtResponse;
import ru.Raingor.webAnimeSite.payload.response.MessageResponse;
import ru.Raingor.webAnimeSite.repository.RoleRepository;
import ru.Raingor.webAnimeSite.repository.UserRepository;
import ru.Raingor.webAnimeSite.security.jwt.JwtUtils;
import ru.Raingor.webAnimeSite.utils.exceptions.UserNotCreatedException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public ResponseEntity<?> authUser(LoginRequest loginRequest, BindingResult bindingResult) {
        //auth user with authenticationManager
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));


        //set auth and generate token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles
                )
        );
    }

    public ResponseEntity<?> regUser(SignUpRequest signUpRequest, BindingResult bindingResults) {
        //check UNIQUE username
        if (userRepository.existsByName(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        //check UNIQUE email
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        if (bindingResults.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResults.getFieldErrors();
            for (FieldError fieldError : errors) {
                errorMsg
                        .append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage());
            }

            throw new UserNotCreatedException(errorMsg.toString());
        }


        //create new user
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getPassword(),
                signUpRequest.getEmail());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userDefaultRole = roleRepository.findByName("ROLE_USER").
                    orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userDefaultRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;

                    default:
                        Role userDefaultRole = roleRepository.findByName("ROLE_USER").
                                orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userDefaultRole);
                }
            });
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
