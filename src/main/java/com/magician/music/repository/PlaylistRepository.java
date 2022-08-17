package com.magician.music.repository;

import com.magician.music.domain.Playlist;
import com.magician.music.domain.PlaylistMusic;
import com.magician.music.domain.PlaylistType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PlaylistRepository {
    private final EntityManager em;

    public void save(Playlist playlist){
        em.persist(playlist);
    }

    public Playlist findPlayedPlaylist(Long userId){
        return em.createQuery("select pl from Playlist pl " +
                "where pl.user.id = :userId " +
                "and pl.playlistType = :playlistType", Playlist.class)
                .setParameter("userId", userId)
                .setParameter("playlistType", PlaylistType.PLAYED)
                .getSingleResult();
    }
}
