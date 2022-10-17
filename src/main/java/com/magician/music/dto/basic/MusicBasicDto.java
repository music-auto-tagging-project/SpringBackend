package com.magician.music.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MusicBasicDto {

    private Long musicId;
    private String musicImage;
    private String musicTitle;
    private List<String> musicArtist;
    private String lyric;

}
