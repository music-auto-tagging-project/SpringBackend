package com.magician.music.service;

import com.magician.music.domain.*;
import com.magician.music.dto.*;
import com.magician.music.feign.RecommendFeignClient;
import com.magician.music.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaylistService {
    private final PlaylistMusicRepository playlistMusicRepository;
    private final PlaylistRepository playlistRepository;

    private final MusicRepository musicRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final UserTagRepository userTagRepository;
    private final MusicService musicService;

    private final RecommendFeignClient recommendFeignClient;

    /* 들은 플레이리스트에 음악 저장 */
    @Transactional
    public void savePlayedMusic(Long userId, Music music) {
        Playlist playlist = playlistRepository.findPlayedPlaylist(userId);

        PlaylistMusic playlistMusic = new PlaylistMusic();
        playlistMusic.setMusic(music);
        playlistMusic.setPlaylist(playlist);

        playlistMusicRepository.save(playlistMusic);
    }

    /* 음악 재생 */
    @Transactional
    public PlayMusicDto playMusic(Long userId, Long musicId) {
        /* 음악, 사용자 찾아옴 */
        Optional<Music> musicOptional = musicRepository.findById(musicId);
        Optional<User> userOptional = userRepository.findById(userId);

        /* 음악 못찾았을 때 예외 throw */
        if (musicOptional.isEmpty()) {

        }

        /* 사용자 정보 오류 있을 때 예외 throw */
        if (userOptional.isEmpty()) {

        }

        /* 음악, 사용자 get */
        Music music = musicOptional.get();
        User user = userOptional.get();


        /* 찾은 음악의 아티스트 이름만 뽑아 리스트로 변경 */
        List<String> artistNameList = musicService.getMusicArtistNameList(music);


        /* 찾은 음악의 태그 이름만 뽑아 리스트로 변경 */
        List<String> musicTagNameList = musicService.getMusicTagNameList(music);


        /* 들은 음악을 다시듣기 playlist에 삽입 */
        savePlayedMusic(userId, music);


        /* 삽입 후 추천 태그 변경 + 추천 음악 변경 */
        RecommendedUserTagDto recommendedUserTagAndMusicDto = requestRecommendedUserTag(userId);
        RecommendedMusicDto recommendedMusicDto = requestRecommendedMusic(userId);


        // 받아온 태그 리스트 DB에 적용
        saveUserTag(user, recommendedUserTagAndMusicDto.getUserTagList());

        //받아온 추천 음악 리스트 DB에 적용
        saveRecommendedMusic(user, recommendedMusicDto.getMusicIdList());

        /* api 명세서에 맞춰 변환 */
        return new PlayMusicDto(
                music.getTitle(),
                artistNameList,
                music.getAlbum().getReleaseDate(),
                music.getLyric(),
                music.getAlbum().getImagePath(),
                music.getYoutubeId(),
                musicTagNameList);
    }

    @Transactional
    public void saveRecommendedMusic(User user, List<Long> musicIdList) {
        Playlist playlist = playlistRepository.findRecommendedPlaylist(user.getId());
        playlistMusicRepository.deleteByPlaylist(playlist);
        musicIdList.stream().forEach(musicId -> {
            playlistMusicRepository.save(
                    new PlaylistMusic(playlist,musicRepository.findById(musicId).get()));
        });
    }

    @Transactional
    public void saveUserTag(User user, List<String> userTagNameList) {

        /* UNFIXED 태그 중에 추천받지 못한 태그 삭제 */
        userTagRepository.findAllByUserIdAndTagType(user.getId(), TagType.UNFIXED).stream()
                .forEach(userTag -> {
                    if(!userTagNameList.contains(userTag.getTag().getName())){
                        userTagRepository.delete(user, userTag);
                    }
                });
        /* 추천받은 태그 중에 기존에 없던 태그 추가 */
        userTagNameList.stream().forEach(userTagName -> {
            /* 이미 있는 태그면 추가하지 않고, 없는 태그일 시에만 추가 */
            if(userTagRepository.findByUserIdAndTagName(user.getId(), userTagName) == null) {
                userTagRepository.save(new UserTag(user, tagRepository.findByName(userTagName), TagType.UNFIXED));
            }
        });


    }

    public RecommendedUserTagDto requestRecommendedUserTag(Long userId) {
        /* UserId를 통한 추천 태그 변경 + 추천 음악 변경 */
        // User의 들은 음악 플레이리스트 찾음
        Playlist playedPlaylist = playlistRepository.findPlayedPlaylist(userId);

        // 들은 음악 플레이리스트의 음악 리스트
        List<PlaylistMusic> playedPlaylistMusicList = playlistMusicRepository.findAllByPlaylist(playedPlaylist);

        // 들은 음악 플레이리스트에 음악이 없을 경우 예외 처리

        // 들은 음악 플레이 리스트의 음악 아이디 리스트
        List<Long> playedPlaylistMusicIdList = playedPlaylistMusicList.stream()
                .map(playlistMusic -> playlistMusic.getMusic().getId())
                .collect(Collectors.toList());

        return recommendFeignClient.getRecommendedUserTag(
                new RecommendedUserTagRequestDto(playedPlaylistMusicIdList, userId));

    }

    public RecommendedMusicDto requestRecommendedMusic(Long userId) {

        return recommendFeignClient.getRecommendedMusic(
                new RecommendedMusicRequestDto(userId));

    }




}
