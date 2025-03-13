package org.nya.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.nya.entities.Anime;
import org.nya.exceptions.AnimeNotFoundException;
import org.nya.exceptions.InvalidAnimeSortFieldException;
import org.nya.exceptions.InvalidSortDirectionException;
import org.nya.services.AnimeService;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAnime(@PathVariable(name = "id") String id) {
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

    @GetMapping
    public ResponseEntity<?> getAnimeListWithPagination(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "sortDirection") String sortDirection
    ) {
        try {
            List<Anime> animeList = animeService
                .getAnimeListWithPagination(page, size, sortField, sortDirection);
            HttpHeaders headers = new HttpHeaders();
            return ResponseEntity.ok().headers(headers).body(animeList);
        } catch (InvalidSortDirectionException | InvalidAnimeSortFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
        }
    }
}
