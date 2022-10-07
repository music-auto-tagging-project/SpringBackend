package com.magician.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecommendedUserTagAndMusicRequestDto {
    List<Long> musicIdList;
    Long userId;
}
