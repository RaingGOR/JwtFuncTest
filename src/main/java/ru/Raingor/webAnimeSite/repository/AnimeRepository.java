package ru.Raingor.webAnimeSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Raingor.webAnimeSite.models.Anime;

import java.util.List;
import java.util.Optional;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    Optional<Anime> findByName(String name);

    List<Anime> findByAuthor(String author);
}
