package com.magician.music.api;

import com.magician.music.dto.request.AddPlaylistRequestDto;
import com.magician.music.dto.request.DeletePlaylistRequestDto;
import com.magician.music.dto.request.addPlaylistMusicRequestDto;
import com.magician.music.dto.response.PlaylistMusicResponseDto;
import com.magician.music.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping("/playlist/add")
    public void addPlaylist(@RequestBody @Valid AddPlaylistRequestDto request){
        playlistService.addPlaylist(request.getUserId(), request.getPlaylistName(), request.getMusicIdList());
    }

    @DeleteMapping("/playlist/delete")
    public void deletePlaylist(@RequestBody @Valid DeletePlaylistRequestDto request){
        playlistService.deletePlaylist(request.getPlaylistId());

    }

    @PostMapping("/playlist/add/music")
    public void addPlaylistMusic(@RequestBody @Valid addPlaylistMusicRequestDto request){
        playlistService.changeMusic(request.getPlaylistId(), request.getMusicIdList());

    }

    @GetMapping("/playlist/music/{playlistId}")
    public PlaylistMusicResponseDto getPlaylistMusic(@PathVariable("playlistId") Long id){
        return playlistService.getPlaylistMusic(id);
    }

    @GetMapping("/playlist/{playlistId}")
    public void getPlaylist(@PathVariable("playlistId") Long id){


    }
}
