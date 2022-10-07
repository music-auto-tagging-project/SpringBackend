package com.magician.music.service;

import com.magician.music.domain.*;
import com.magician.music.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;

    public List<String> getMusicArtistNameList(Music music) {
        List<MusicArtist> artistList = music.getArtistList();
        return artistList.stream()
                .map(artist -> artist.getArtist().getName())
                .collect(Collectors.toList());
    }

    public List<String> getMusicTagNameList(Music music) {

        List<MusicTag> musicTagList = music.getTagList();
        return musicTagList.stream()
                .map(musicTag -> musicTag.getTag().getName())
                .collect(Collectors.toList());
    }
}
