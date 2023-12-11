package ru.Raingor.webAnimeSite.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Raingor.webAnimeSite.dtos.UserDTO;
import ru.Raingor.webAnimeSite.models.User;
import ru.Raingor.webAnimeSite.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/*
 * Тесты написаны впервые, все еще юзаю gpt для того, что бы изучать JUNIT
 * (чет ошибка java.lang.NullPointerException: Cannot invoke "org.modelmapper.ModelMapper.map(Object, java.lang.Class)" because "this.modelMapper" is null)
 валит, так что тесты оставим до лучших времен
 * @Mock: Аннотация для создания мока объекта. В данном случае, это userServiceMock.
 * @InjectMocks: Аннотация для создания экземпляра класса, в который будут внедрены зависимости (в данном случае, yourController).
 * when(userServiceMock.getAllUsers()).thenReturn(fakeUsers): Когда вызывается метод getAllUsers у userServiceMock,
    вернуть фиктивный список пользователей.
 * verify(userServiceMock, times(1)).getAllUsers(): Проверяем, что метод getAllUsers был вызван ровно один раз.
 * assertEquals(expectedUserDTOs, actualUserDTOs): Проверяем, что возвращенный список UserDTO соответствует ожидаемому.
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userController;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this); // Инициализация моков
//    }


    @Test
    public void testGetAllUsers() {
        // Создаем фиктивные данные для теста
        List<User> fakeUsers = Arrays.asList(
                new User("Money", "1234", "gabenLoveMoney@gmail.com"),
                new User("HALF-LIFE3", "1234", "totalSecretValve@gmail.com"),
                new User("DOTA3", "1234", "gaben@gmail.com")
        );

        List<UserDTO> expectedUserDTOs = Arrays.asList(
                userController.convertToUserDTO(fakeUsers.get(0)),
                userController.convertToUserDTO(fakeUsers.get(1)),
                userController.convertToUserDTO(fakeUsers.get(2))

        );


        //Настроим мок для userService.getAllUsers
        when(userServiceMock.getAllUsers()).thenReturn(fakeUsers);

        // Вызываем метод контроллера
        List<UserDTO> actualUserDTOs = userController.getAllUsers();

        // Проверяем, что метод userServiceMock.getAllUsers был вызван один раз
        verify(userServiceMock, times(1)).getAllUsers();

        // Проверяем, что возвращенные данные соответствуют ожидаемым
        assertEquals(expectedUserDTOs, actualUserDTOs);

    }
}
