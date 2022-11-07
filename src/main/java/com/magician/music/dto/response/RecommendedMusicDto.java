package com.magician.music.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecommendedMusicDto {
    List<Long> musicIdList;

    public RecommendedMusicDto(){

    }
}
