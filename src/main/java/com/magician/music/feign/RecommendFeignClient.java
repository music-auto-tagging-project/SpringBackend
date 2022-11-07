package com.magician.music.feign;

import com.magician.music.dto.response.RecommendedMusicDto;
import com.magician.music.dto.request.RecommendedMusicRequestDto;
import com.magician.music.dto.response.RecommendedUserTagDto;
import com.magician.music.dto.request.RecommendedUserTagRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "recommendFeignClient", url = "10.1.3.30:5000")
public interface RecommendFeignClient {

    /* playlist music id, user id 를 넘겨주어 유저 태그 추천 */
    @RequestMapping(method = RequestMethod.POST, value = "/recommend/user-tag")
    RecommendedUserTagDto getRecommendedUserTag(
            @RequestBody @Validated RecommendedUserTagRequestDto recommendedUserTagRequestDto);

    @RequestMapping(method = RequestMethod.POST, value = "/recommend/music")
    RecommendedMusicDto getRecommendedMusic(
            @RequestBody @Validated RecommendedMusicRequestDto recommendedMusicRequestDto);



}
