package com.magician.music.api;

import com.magician.music.dto.ArtistDto;
import com.magician.music.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArtistApiController {

    private final ArtistService artistService;

    @GetMapping("artist/info/{artistId}")
    public ArtistDto searchArtistById(@PathVariable("artistId") Long artistId) {
        return artistService.getArtistInfo(artistId);
    }
}
