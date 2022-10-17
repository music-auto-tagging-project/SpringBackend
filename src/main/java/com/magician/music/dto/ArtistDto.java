package com.magician.music.dto;

import com.magician.music.dto.basic.MusicBasicDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ArtistDto {
    private Long id;
    private String name;
    private String imagePath;
    private String agency;
    private LocalDate debutDate;
    private List<MusicBasicDto> musicList;
}
