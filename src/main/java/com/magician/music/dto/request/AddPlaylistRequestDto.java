package com.magician.music.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddPlaylistRequestDto {
    private Long userId;
    private String playlistName;
    private List<Long> musicIdList;

}
