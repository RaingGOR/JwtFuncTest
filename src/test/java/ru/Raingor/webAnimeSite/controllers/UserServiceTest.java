package ru.Raingor.webAnimeSite.controllers;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.internal.util.Assert;
import ru.Raingor.webAnimeSite.models.User;
import ru.Raingor.webAnimeSite.repository.UserRepository;
import ru.Raingor.webAnimeSite.service.UserService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class UserServiceTest {
    @Mock           //имитация данного репозитория
    private UserRepository userRepository;
    @InjectMocks    //вызывать для проверки в тестах
    private UserService userService;

    @Test
    void countUsers(){
        User user1 = new User("name","1234","mail@gmail.com");
        User user2 = new User("name2","1234","mail@gmail.com");
        // при вызове из репы findAll вернем лист
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1,user2));

        int countUserInBd = userService.countUsers();
        Assertions.assertEquals(2, countUserInBd);
    }
}
