package com.magician.music.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePlaylistRequestDto {
    private Long userId;
    private Long playlistId;
}
