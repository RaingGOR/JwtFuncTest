package ru.Raingor.webAnimeSite.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.Raingor.webAnimeSite.dtos.RegistrationUserDto;
import ru.Raingor.webAnimeSite.dtos.UserDTO;
import ru.Raingor.webAnimeSite.models.User;
import ru.Raingor.webAnimeSite.service.UserService;
import ru.Raingor.webAnimeSite.utils.UserErrorResponse;
import ru.Raingor.webAnimeSite.exceptions.UserNotCreatedException;
import ru.Raingor.webAnimeSite.exceptions.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    //Получение списка всех пользователей:
    //Get a list with all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    //Получение информации о конкретном пользователе по ID:
    //Get info about a specific  user by id
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") int id) {
        return convertToUserDTO(userService.getUserById(id));
    }

    //Создание нового пользователя:
    //create a new user
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createNewUser(@RequestBody @Valid RegistrationUserDto registrationUserDto
            , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError fieldError : errors) {
                errorMsg
                        .append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotCreatedException(errorMsg.toString());
        }

        userService.saveUserInDataBase(userService.convertToUser(registrationUserDto));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Обновление информации о пользователе:
    //update user information
    @PatchMapping("/{id}/update")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") int id,
                                                 @RequestBody RegistrationUserDto updatedUserDTO) {
        userService.updateUserInDB(id, userService.convertToUser(updatedUserDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Удаление пользователя:
    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    //Дополнительные операции, например, поиск пользователей по имени или электронной почте:
    //Управление ролями и правами доступа (если это применимо):


    //convert User -> UserDTO
    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    // обработка исключений
    // exception handling

    // валидация при создании человека
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
