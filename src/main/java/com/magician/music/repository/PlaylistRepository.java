package com.magician.music.repository;

import com.magician.music.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PlaylistRepository {
    private final EntityManager em;

    public Playlist save(Playlist playlist){
        em.persist(playlist);
        return playlist;
    }

    public Playlist findPlayedPlaylist(Long userId){
        return em.createQuery("select pl from Playlist pl " +
                "where pl.user.id = :userId " +
                "and pl.playlistType = :playlistType", Playlist.class)
                .setParameter("userId", userId)
                .setParameter("playlistType", PlaylistType.PLAYED)
                .getSingleResult();
    }

    public Playlist findRecommendedPlaylist(Long userId){
        return em.createQuery("select pl from Playlist pl " +
                        "where pl.user.id = :userId " +
                        "and pl.playlistType = :playlistType", Playlist.class)
                .setParameter("userId", userId)
                .setParameter("playlistType", PlaylistType.RECOMMENDED)
                .getSingleResult();
    }

    public void deleteById(Long playlistId){
        em.createQuery("delete from Playlist p where p.id = :id")
                .setParameter("id", playlistId)
                .executeUpdate();
    }

    public Playlist findById(Long playlistId){
        return em.createQuery("select pl from Playlist pl " +
                "where pl.id = :id", Playlist.class)
                .setParameter("id", playlistId)
                .getSingleResult();
    }
}
