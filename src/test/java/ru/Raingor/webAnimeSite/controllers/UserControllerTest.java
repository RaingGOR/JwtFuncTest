package ru.Raingor.webAnimeSite.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
 валит, так что модел маппер оставим до лучших времен, пока ручками)
 * @Mock: Аннотация для создания мока объекта. В данном случае, это userServiceMock.
 * @InjectMocks: Аннотация для создания экземпляра класса, в который будут внедрены зависимости (в данном случае, yourController).
 * when(userServiceMock.getAllUsers()).thenReturn(fakeUsers): Когда вызывается метод getAllUsers у userServiceMock,
    вернуть фиктивный список пользователей.
 * verify(userServiceMock, times(1)).getAllUsers(): Проверяем, что метод getAllUsers был вызван ровно один раз.
 * assertEquals(expectedUserDTOs, actualUserDTOs): Проверяем, что возвращенный список UserDTO соответствует ожидаемому.
 */

@ExtendWith(MockitoExtension.class)
@Nested
@DisplayName("UserController test")
//@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userController;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this); // Инициализация моков
//    }


//    @Test
//    public void testGetAllUsers() {
//        // Создаем фиктивные данные для теста
//        List<User> fakeUsers = Arrays.asList(
//                new User("Money", "1234", "gabenLoveMoney@gmail.com"),
//                new User("HALF-LIFE3", "1234", "totalSecretValve@gmail.com"),
//                new User("DOTA3", "1234", "gaben@gmail.com")
//        );
//
//        List<UserDTO> expectedUserDTOs = Arrays.asList(
//                userServiceMock.convertToUserDTO(fakeUsers.get(0)),
//                userServiceMock.convertToUserDTO(fakeUsers.get(1)),
//                userServiceMock.convertToUserDTO(fakeUsers.get(2))
//
//        );
//
//
//        //Настроим мок для userService.getAllUsers
//        when(userServiceMock.getAllUsers()).thenReturn(fakeUsers);
//
//        // Вызываем метод контроллера
//        List<UserDTO> actualUserDTOs = userController.getAllUsers();
//
//        // Проверяем, что метод userServiceMock.getAllUsers был вызван один раз
//        verify(userServiceMock, times(1)).getAllUsers();
//
//        // Проверяем, что возвращенные данные соответствуют ожидаемым
//        assertEquals(expectedUserDTOs, actualUserDTOs);
//
//    }

//    @Test
//    public void testGetUserById() {
//        // Создаем фиктивные данные для теста
//        UserDTO fakeUser = new UserDTO();
//        fakeUser.setId(1);
//        fakeUser.setName("Money");
//        fakeUser.setEmail("money@mail.ru");
//
//        when(userServiceMock.getUserById(1)).thenReturn(fakeUser);
//
//        UserDTO user = userController.getUserById(1);
//
//        verify(userServiceMock, times(1)).getUserById(1);
//
//        assertEquals(fakeUser, user);
//    }
}
