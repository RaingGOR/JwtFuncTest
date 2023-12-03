package ru.Raingor.webAnimeSite.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Raingor.webAnimeSite.dtos.UserDTO;
import ru.Raingor.webAnimeSite.models.User;
import ru.Raingor.webAnimeSite.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    //Получение списка всех пользователей:
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    //Получение информации о конкретном пользователе по ID:
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") int id) {
        return convertToUserDTO(userService.getUserById(id));
    }

    //Создание нового пользователя:
    @PostMapping
    public ResponseEntity<HttpStatus> createNewUser(@RequestBody UserDTO userDTO) {
        userService.saveUserInDataBase(convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    //Обновление информации о пользователе:

    //Удаление пользователя:
    //Дополнительные операции, например, поиск пользователей по имени или электронной почте:
    //Управление ролями и правами доступа (если это применимо):

    //Convert UserDTO -> User
    private User convertToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    //convert User -> UserDTO
    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
