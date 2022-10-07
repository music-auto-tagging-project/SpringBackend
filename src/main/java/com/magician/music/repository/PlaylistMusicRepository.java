package com.magician.music.repository;

import com.magician.music.domain.Playlist;
import com.magician.music.domain.PlaylistMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface PlaylistMusicRepository extends JpaRepository<PlaylistMusic, Long> {
    List<PlaylistMusic> findAllByPlaylist(Playlist playlist);
    void deleteByPlaylist(Playlist playlist);
}
