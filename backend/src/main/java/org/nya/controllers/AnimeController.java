package org.nya.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.Optional;

import org.nya.entities.Anime;
import org.nya.exceptions.AnimeNotFoundException;
import org.nya.services.AnimeService;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<?> getAnime(@RequestParam(name = "id") String id) {
        try {
            // TODO: Use a DTO.
            Anime anime = animeService.getAnimeById(id);
            HttpHeaders headers = new HttpHeaders();
            return ResponseEntity.ok().headers(headers).body(anime);
        } catch (AnimeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
        }
    }
}
