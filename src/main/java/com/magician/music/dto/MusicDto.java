package com.magician.music.dto;

import com.magician.music.dto.basic.ArtistBasicDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MusicDto {
    private Long id;
    private String title;
    private String lyric;
    private String imagePath;
    private List<ArtistBasicDto> artistList;
    private List<String> tagNameList;

}
