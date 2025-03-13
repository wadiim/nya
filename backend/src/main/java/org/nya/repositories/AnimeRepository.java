package org.nya.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.nya.entities.Anime;
import org.nya.exceptions.InvalidAnimeSortFieldException;
import org.nya.utils.SortDirection;

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

    public List<Anime> findAllWithPagination(
            int page,
            int size,
            String sortField,
            SortDirection sortDirection
    ) throws InvalidAnimeSortFieldException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Anime> cq = cb.createQuery(Anime.class);
        Root<Anime> root = cq.from(Anime.class);

        try {
            switch (sortDirection) {
                case ASCENDING:
                    cq.orderBy(cb.asc(root.get(sortField)));
                    break;
                case DESCENDING:
                    cq.orderBy(cb.desc(root.get(sortField)));
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidAnimeSortFieldException(
                    "Invalid sort field: " + sortField
            );
        }

        TypedQuery<Anime> query = entityManager.createQuery(cq);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }
}
