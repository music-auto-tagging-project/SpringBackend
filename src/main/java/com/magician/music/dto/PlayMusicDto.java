package com.magician.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class PlayMusicDto {
    private String musicTitle;
    private List<String> artist;
    private LocalDate releaseDate;
    private String musicLyric;
    private String albumImage;
    private String youtubeId;
    private List<String> tagList;
}
