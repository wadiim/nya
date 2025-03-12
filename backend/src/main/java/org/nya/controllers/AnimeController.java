package org.nya.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.Optional;

import lombok.extern.java.Log;

import org.nya.services.AnimeService;

@Log
@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    // TODO: Make it return the `Anime` entity as JSON instead of only the
    //       `title` string.
    @GetMapping
    public String getAnime(@RequestParam(name = "id") String id) {
        return animeService.find(id);
    }
}
