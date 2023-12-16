package ru.Raingor.webAnimeSite.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.Raingor.webAnimeSite.dtos.RegistrationUserDto;
import ru.Raingor.webAnimeSite.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //Get a list with all users
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsersDto();
    }

    //Get info about a specific  user by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return userService.getUserDtoById(id);
    }

    //create a new user
    @Deprecated //soon this method is deleted
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid RegistrationUserDto registrationUserDto
            , BindingResult bindingResult) {
        return userService.createNewUser(registrationUserDto, bindingResult);
    }

    //update user information
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                        @RequestBody RegistrationUserDto updatedUserDTO) {
        return userService.updateUser(id, updatedUserDTO);
    }

    //delete user
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUserControl(id);
    }

}
