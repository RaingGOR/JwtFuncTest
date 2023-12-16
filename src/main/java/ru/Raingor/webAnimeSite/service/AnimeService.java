package ru.Raingor.webAnimeSite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.Raingor.webAnimeSite.dtos.AnimeDTO;
import ru.Raingor.webAnimeSite.models.Anime;
import ru.Raingor.webAnimeSite.repository.AnimeRepository;
import ru.Raingor.webAnimeSite.utils.exceptions.anime.AnimeNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public ResponseEntity<?> getAllAnime() {
        List<Anime> animeList = animeRepository.findAll();
        List<AnimeDTO> animeDTOS = animeList.stream().map(this::convertToAnimeDTO).collect(Collectors.toList());

        return new ResponseEntity<>(animeDTOS, HttpStatus.OK);
    }

    public AnimeDTO convertToAnimeDTO(Anime anime) {
        return new AnimeDTO(
                anime.getName(),
                anime.getAuthor(),
                anime.getCountSeries(),
                anime.getDescription(),
                anime.getRating()
        );
    }

    public ResponseEntity<?> getAnimeById(Long id) {
        Anime anime = animeRepository.findById(id).orElseThrow(AnimeNotFoundException::new);
        AnimeDTO animeDTO = convertToAnimeDTO(anime);
        return new ResponseEntity<>(animeDTO, HttpStatus.OK);
    }
}
