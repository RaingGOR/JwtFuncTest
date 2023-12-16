package ru.Raingor.webAnimeSite.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Raingor.webAnimeSite.dtos.AnimeDTO;
import ru.Raingor.webAnimeSite.service.AnimeService;

/*
 * Collections page users
 * * name collection
 * * author collection
 * * collection rating
 * * count anime
 * * description
 * * List Anime
 * * commentaries
 *
 */
@RestController
@RequestMapping("/api/anime")
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<?> allAnimePage() {
        return animeService.getAllAnime();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimePageById(@PathVariable Long id) {
        return animeService.getAnimeById(id);
    }

}
