package org.nya.services;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.nya.entities.Anime;
import org.nya.exceptions.AnimeNotFoundException;
import org.nya.exceptions.InvalidAnimeSortFieldException;
import org.nya.exceptions.InvalidSortDirectionException;
import org.nya.repositories.AnimeRepository;
import org.nya.utils.SortDirection;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime getAnimeById(UUID id) throws AnimeNotFoundException {
        Anime anime = animeRepository
            .find(id)
            .orElseThrow(AnimeNotFoundException::new);

        return anime;
    }

    // TODO: Add base class for exceptions
    public List<Anime> getAnimeListWithPagination(
            int page,
            int size,
            String sortField,
            String sortDirection
    ) throws InvalidSortDirectionException, InvalidAnimeSortFieldException {
        SortDirection sortDir = SortDirection.from(sortDirection);
        List<Anime> animeList = animeRepository
            .findAllWithPagination(page, size, sortField, sortDir);

        return animeList;
    }
}
