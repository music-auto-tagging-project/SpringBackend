package com.magician.music.service;

import com.magician.music.domain.Music;
import com.magician.music.domain.Playlist;
import com.magician.music.domain.PlaylistMusic;
import com.magician.music.domain.User;
import com.magician.music.repository.PlaylistMusicRepository;
import com.magician.music.repository.PlaylistRepository;
import com.magician.music.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistMusicRepository playlistMusicRepository;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    @Transactional
    public void savePlayedMusic(Long userId, Music music){
        Playlist playlist = playlistRepository.findPlayedPlaylist(userId);
        PlaylistMusic playlistMusic = new PlaylistMusic();
        playlistMusic.setMusic(music);
        playlistMusic.setPlaylist(playlist);
        playlistMusicRepository.save(playlistMusic);
    }
}
