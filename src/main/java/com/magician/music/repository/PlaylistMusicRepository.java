package com.magician.music.repository;

import com.magician.music.domain.Playlist;
import com.magician.music.domain.PlaylistMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PlaylistMusicRepository {
    private final EntityManager em;

    public void save(PlaylistMusic playlistMusic){
        em.persist(playlistMusic);
    }


}
