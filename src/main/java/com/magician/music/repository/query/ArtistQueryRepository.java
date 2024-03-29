package com.magician.music.repository.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArtistQueryRepository {

    private final EntityManager em;


    public List<ArtistQueryDto> findAllArtistName(){
        return em.createQuery(
                        "select new com.magician.music.repository.query.ArtistQueryDto(a.id, a.name)" +
                                " from Artist a"
                        , ArtistQueryDto.class)
                .getResultList();
    }

    public List<ArtistQueryDto> findAllArtistNameByName(String content) {
        return em.createQuery(
                        "select new com.magician.music.repository.query.ArtistQueryDto(a.id, a.name)" +
                                " from Artist a" +
                                " where a.name like concat('%', :artist, '%')"
                        , ArtistQueryDto.class)
                .setParameter("artist", content)
                .getResultList();
    }
}
