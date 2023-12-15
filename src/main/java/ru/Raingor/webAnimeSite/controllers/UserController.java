package ru.Raingor.webAnimeSite.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.Raingor.webAnimeSite.dtos.RegistrationUserDto;
import ru.Raingor.webAnimeSite.utils.exceptions.UserNotCreatedException;
import ru.Raingor.webAnimeSite.utils.exceptions.UserNotFoundException;
import ru.Raingor.webAnimeSite.service.UserService;
import ru.Raingor.webAnimeSite.utils.UserErrorResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //Get a list with all users
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsersDto();
    }

    //Get info about a specific  user by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        return userService.getUserDtoById(id);
    }

    //create a new user
    @PostMapping("/new")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid RegistrationUserDto registrationUserDto
            , BindingResult bindingResult) {
        return userService.createNewUser(registrationUserDto, bindingResult);
    }

    //update user information
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id,
                                        @RequestBody RegistrationUserDto updatedUserDTO) {
        return userService.updateUser(id, updatedUserDTO);
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        return userService.deleteUserControl(id);
    }

    // exception handling
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleExecption(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        //return 400
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleExecption(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User not found",
                System.currentTimeMillis()
        );

        //return 404
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
