package com.magician.music.service;

import com.magician.music.domain.Artist;
import com.magician.music.domain.Music;
import com.magician.music.dto.ArtistDto;
import com.magician.music.dto.basic.MusicBasicDto;
import com.magician.music.repository.ArtistRepository;
import com.magician.music.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final MusicRepository musicRepository;

    private final MusicService musicService;

    /*
        아티스트 id로 아티스트 정보 조회
     */
    public ArtistDto getArtistInfo(Long id){
        /* 찾을 수 없을 때 에러 처리 */
        Artist artist = artistRepository.findById(id).get();

        return new ArtistDto(
                artist.getId(),
                artist.getName(),
                artist.getImagePath(),
                artist.getAgency(),
                artist.getDebutDate(),
                artist.getMusicList().stream()
                        .map(music-> getMusicBasicInfo(music.getMusic().getId()))
                        .collect(Collectors.toList()));
    }

    public MusicBasicDto getMusicBasicInfo(Long id){
        Music music = musicRepository.findById(id).get();
        return new MusicBasicDto(
                music.getId(),
                music.getAlbum().getImagePath(),
                music.getTitle(),
                musicService.getMusicArtistNameList(music),
                musicService.getMusicTagNameList(music),
                music.getLyric()
                );
    }
}
