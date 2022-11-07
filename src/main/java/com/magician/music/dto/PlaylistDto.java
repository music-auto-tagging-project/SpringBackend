package com.magician.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaylistDto {
    private Long id;
    private String name;
    private String imagePath;
    private Long musicCount;
}
