package com.magician.music.repository.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter @Setter
public class MusicQueryDto {
    public MusicQueryDto(Long musicId, String musicImage, String musicTitle) {
        this.musicId = musicId;
        this.musicImage = musicImage;
        this.musicTitle = musicTitle;
    }

    private Long musicId;
    private String musicImage;
    private String musicTitle;
    private List<String> musicArtist;
}
