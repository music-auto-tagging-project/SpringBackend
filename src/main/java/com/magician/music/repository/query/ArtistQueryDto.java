package com.magician.music.repository.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ArtistQueryDto {
    private Long id;
    private String name;
}