package com.magician.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecommendedUserTagAndMusicDto {
    List<String> userTagList;
    List<Long> musicIdList;
}
