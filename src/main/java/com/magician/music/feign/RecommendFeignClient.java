package com.magician.music.feign;

import com.magician.music.dto.RecommendedUserTagAndMusicDto;
import com.magician.music.dto.RecommendedUserTagAndMusicRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "recommendFeignClient", url = "10.1.3.30:5000")
public interface RecommendFeignClient {

    /* playlist music id, user id 를 넘겨주면 유저 태그와 음악 추천을 해 줌 */
    @RequestMapping(method = RequestMethod.POST, value = "/recommend/user-tag-music")
    RecommendedUserTagAndMusicDto getRecommendedUserTagAndMusic(
            @RequestBody @Validated RecommendedUserTagAndMusicRequestDto recommendedUserTagAndMusicRequestDto);



}
