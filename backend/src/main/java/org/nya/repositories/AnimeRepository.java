package org.nya.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.UUID;
import java.util.Optional;

import org.nya.entities.Anime;

@Repository
@Transactional
public class AnimeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Anime> find(UUID id) {
        Optional<Anime> optEntity = Optional.ofNullable(
            this.entityManager.find(Anime.class, id)
        );
        optEntity.ifPresent(e -> entityManager.refresh(e));
        return optEntity;
    }
}
