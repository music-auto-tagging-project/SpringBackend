package com.magician.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AlbumDto {
    private Long id;
    private String name;
    private String imagePath;
    private LocalDate releaseDate;
}
