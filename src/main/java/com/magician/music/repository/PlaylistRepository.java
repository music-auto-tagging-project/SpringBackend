package com.magician.music.repository;

import com.magician.music.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Playlist> findAllByUser(User user) {
        return em.createQuery("select pl from Playlist pl " +
                        "where pl.user.id = :id", Playlist.class)
                .setParameter("id", user.getId())
                .getResultList();
    }
}
