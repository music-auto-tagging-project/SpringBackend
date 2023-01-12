package com.magician.music.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class addPlaylistMusicRequestDto {
    private Long playlistId;
    private List<Long> musicIdList;

}
