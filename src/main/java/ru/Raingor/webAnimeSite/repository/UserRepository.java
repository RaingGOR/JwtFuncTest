package ru.Raingor.webAnimeSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Raingor.webAnimeSite.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    public User findByEmail(String email);

    Boolean existsByName(String username);

    Boolean existsByEmail(String email);


}
