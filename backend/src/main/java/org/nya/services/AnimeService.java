package org.nya.services;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
import java.util.Optional;

import org.nya.entities.Anime;
import org.nya.exceptions.AnimeNotFoundException;
import org.nya.repositories.AnimeRepository;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime getAnimeById(String id) throws AnimeNotFoundException {
        // TODO: Handle invalid `UUID`s.
        Anime anime = animeRepository
            .find(UUID.fromString(id))
            .orElseThrow(AnimeNotFoundException::new);

        return anime;
    }
}
