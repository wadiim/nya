package org.nya.services;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
import java.util.Optional;

import org.nya.repositories.AnimeRepository;
import org.nya.entities.Anime;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public String find(String id) {
        // TODO: Handle invalid `UUID`s.
        // TODO: Throw an exception when the entity with the given `id` was
        //       not found.
        Optional<Anime> optEntity = animeRepository.find(UUID.fromString(id));
        return (optEntity.isPresent()) ? optEntity.get().getTitle() : "NULL";
    }
}
