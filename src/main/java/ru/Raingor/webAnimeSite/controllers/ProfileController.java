package ru.Raingor.webAnimeSite.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Raingor.webAnimeSite.service.UserService;

@RestController
@RequestMapping("/api/profile")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getProfileInformation() {
        return userService.getProfileInformation();
    }

}
