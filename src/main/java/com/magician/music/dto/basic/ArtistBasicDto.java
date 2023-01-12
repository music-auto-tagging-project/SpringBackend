package com.magician.music.dto.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ArtistBasicDto {
    private Long id;
    private String name;
    private String imagePath;
    private String agency;
    private LocalDate debutDate;
}
