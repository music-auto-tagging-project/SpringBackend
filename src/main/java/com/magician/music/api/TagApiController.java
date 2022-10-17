package com.magician.music.api;

import com.magician.music.service.TagService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagApiController {
    private final TagService tagService;

    @GetMapping("tag")
    public TagDto getTagNameList(){
        return new TagDto(tagService.getTagNameList());
    }


    @Data
    @AllArgsConstructor
    private class TagDto{
        private List<String> tagList;
    }



}
