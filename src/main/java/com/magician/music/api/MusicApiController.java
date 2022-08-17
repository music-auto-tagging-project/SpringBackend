package com.magician.music.api;

import com.magician.music.domain.Music;
import com.magician.music.domain.Playlist;
import com.magician.music.service.MusicService;
import com.magician.music.service.PlaylistService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MusicApiController {
    private final MusicService musicService;
    private final PlaylistService playlistService;

    @GetMapping("music/stream/{userId}/{musicId}")
    public MusicDto playMusic(@PathVariable("userId") Long userId, @PathVariable("musicId") Long musicId){
        Music music = musicService.findMusic(musicId);

        /* 음악 못찾았을 때 예외처리 */



        /* 찾은 음악의 아티스트 이름만 뽑아 리스트로 변경 */
        List<String> artistList = new ArrayList<>();
        artistList = music.getArtistList().stream()
                .map(a -> a.getArtist().getName())
                .collect(Collectors.toList());

        /* 찾은 음악의 태그 이름만 뽑아 리스트로 변경 */
        List<String> tagList = new ArrayList<>();
        tagList = music.getTagList().stream()
                .map(a -> a.getTag().getName())
                .collect(Collectors.toList());

        /* 들은 음악을 다시듣기 playlist에 삽입 */
        playlistService.savePlayedMusic(userId, music);

        /* 삽입 후 추천 태그 변경 + 추천 음악 변경 */
        

        /* api 명세서에 맞춰 변환 */
        MusicDto musicDto = new MusicDto(music.getTitle(),
                artistList,
                music.getAlbum().getReleaseDate(),
                music.getLyric(),
                music.getAlbum().getImagePath(),
                music.getYoutubeId(),
                tagList);



        return musicDto;
    }


    @Data
    @AllArgsConstructor
    private class MusicDto {
        private String musicTitle;
        private List<String> artist;
        private LocalDate releaseDate;
        private String musicLyric;
        private String albumImage;
        private String youtubeId;
        private List<String> tagList;
    }
}
