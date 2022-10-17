package com.magician.music.dto;

import com.magician.music.repository.query.ArtistQueryDto;
import com.magician.music.repository.query.MusicQueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchDto {
    private List<MusicQueryDto> musicNameList;
    private List<ArtistQueryDto> artistNameList;
    private List<String> tagList;
}
