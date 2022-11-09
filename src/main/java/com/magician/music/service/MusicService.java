package com.magician.music.service;

import com.magician.music.domain.*;
import com.magician.music.dto.MusicDto;
import com.magician.music.dto.SearchDto;
import com.magician.music.dto.basic.ArtistBasicDto;
import com.magician.music.dto.response.MusicListDto;
import com.magician.music.repository.query.ArtistQueryDto;
import com.magician.music.repository.query.ArtistQueryRepository;
import com.magician.music.repository.ArtistRepository;
import com.magician.music.repository.MusicRepository;
import com.magician.music.repository.query.MusicQueryDto;
import com.magician.music.repository.query.MusicQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final MusicQueryRepository musicQueryRepository;
    private final ArtistRepository artistRepository;
    private final ArtistQueryRepository artistQueryRepository;
    private final TagService tagService;

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

    public SearchDto search() {
        List<MusicQueryDto> musicNameList = musicQueryRepository.findAllMusicName();
        List<ArtistQueryDto> artistNameList = artistQueryRepository.findAllArtistName();
        List<String> tagNameList =  tagService.getTagNameList();
        return new SearchDto(musicNameList, artistNameList, tagNameList);
    }

    public MusicDto getMusicInfo(Long id){
        Music music = musicRepository.findById(id).get();
        return new MusicDto(
                music.getId(),
                music.getTitle(),
                music.getLyric(),
                music.getAlbum().getImagePath(),
                music.getArtistList().stream()
                        .map( artist -> getArtistBasicInfo(artist.getArtist().getId()))
                        .collect(Collectors.toList()),
                getMusicTagNameList(music));
    }
    public ArtistBasicDto getArtistBasicInfo(Long id){
        Artist artist = artistRepository.findById(id).get();
        return new ArtistBasicDto(
                artist.getId(),
                artist.getName(),
                artist.getImagePath(),
                artist.getAgency(),
                artist.getDebutDate());

    }

    public SearchDto searchContent(String content) {
        List<MusicQueryDto> musicNameList = musicQueryRepository.findAllMusicNameByName(content);
        List<ArtistQueryDto> artistNameList = artistQueryRepository.findAllArtistNameByName(content);
        List<String> tagNameList =  tagService.getTagNameListByName(content);
        return new SearchDto(musicNameList, artistNameList, tagNameList);
    }

    public MusicListDto getMusicByTagName(String tagName) {
        List<Music> musicList = musicQueryRepository.findAllByTag(tagService.findTagByName(tagName));
        List<MusicDto> musicDtoList = new ArrayList<>();
        musicList.stream().forEach(music -> {
            musicDtoList.add(getMusicInfo(music.getId()));
        });
        return new MusicListDto(musicDtoList);
    }
}
