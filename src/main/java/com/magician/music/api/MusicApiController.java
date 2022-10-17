package com.magician.music.api;

import com.magician.music.dto.MusicDto;
import com.magician.music.dto.PlayMusicDto;
import com.magician.music.service.MusicService;
import com.magician.music.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MusicApiController {
    private final PlaylistService playlistService;
    private final MusicService musicService;

    @GetMapping("music/stream/{userId}/{musicId}")
    public PlayMusicDto playMusic(@PathVariable("userId") Long userId, @PathVariable("musicId") Long musicId) {
        return playlistService.playMusic(userId, musicId);
    }

    @GetMapping("music/info/{musicId}")
    public MusicDto searchMusicById(@PathVariable("musicId") Long musicId){
        return musicService.getMusicInfo(musicId);
    }
}
