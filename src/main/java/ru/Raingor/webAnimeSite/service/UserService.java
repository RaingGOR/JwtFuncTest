package ru.Raingor.webAnimeSite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Raingor.webAnimeSite.models.User;
import ru.Raingor.webAnimeSite.repository.UserRepository;
import ru.Raingor.webAnimeSite.utils.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        Optional<User> foundUserById = userRepository.findById(id);
        return foundUserById.orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void saveUserInDataBase(User user) {
        userRepository.save(user);
    }
}
