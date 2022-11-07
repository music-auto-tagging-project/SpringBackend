package com.magician.music.dto.response;

import com.magician.music.dto.MusicDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaylistMusicResponseDto {
    private List<MusicDto> musicList;
    private String playlistName;
}
