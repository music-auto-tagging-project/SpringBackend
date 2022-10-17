package com.magician.music.api;

import com.magician.music.dto.SearchDto;

import com.magician.music.service.ArtistService;
import com.magician.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchApiController {
    private final MusicService musicService;
    private final ArtistService artistService;

    @GetMapping("search")
    public SearchDto search() {
        return musicService.search();
    }


}
