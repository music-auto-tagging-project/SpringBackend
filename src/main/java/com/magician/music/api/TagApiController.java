package com.magician.music.api;

import com.magician.music.domain.Tag;
import com.magician.music.domain.TagType;
import com.magician.music.service.TagService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TagApiController {
    private final TagService tagService;

    @GetMapping("tag")
    public TagDto getTagList(){

        List<String> tagList =  tagService.findTags()
                .stream()
                .map(t->t.getName())
                .collect(Collectors.toList());

        return new TagDto(tagList);
    }


    @Data
    @AllArgsConstructor
    private class TagDto{
        private List<String> tagList;
    }



}
