package ru.Raingor.webAnimeSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Raingor.webAnimeSite.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);

    public User findByEmail(String email);
}
