package com.magician.music.api;

import com.magician.music.domain.PlaylistMusic;
import com.magician.music.domain.PlaylistType;
import com.magician.music.domain.TagType;
import com.magician.music.domain.User;
import com.magician.music.repository.music.query.MusicDto;
import com.magician.music.repository.music.query.MusicQueryRepository;
import com.magician.music.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final MusicQueryRepository musicQueryRepository;

    @GetMapping("user/info/{userId}")
    public UserDto getUserPageUserInfo(@PathVariable("userId") Long id) {

        User user = userService.findOne(id);

        /* user가 가지고 있는 tag name list (DELETED 제외) */
        List<String> tagList = user.getTagList().stream()
                .filter(t -> t.getTagType() != TagType.DELETED)
                .map(t -> t.getTag().getName())
                .collect(Collectors.toList());


        return new UserDto(user.getImagePath(), user.getName(), tagList);
    }

    @GetMapping("main/{userId}")
    public MainDto getMainPageUserInfo(@PathVariable("userId") Long id) {
        User user = userService.findOne(id);

        /* user가 가지고 있는 tag name list */
        List<String> tagList = user.getTagList().stream()
                .map(t -> t.getTag().getName())
                .collect(Collectors.toList());


        List<MusicDto> recommendMusicDtoList = musicQueryRepository.getMusicArtistList(musicQueryRepository.findMusicListByPlaylistType(id, PlaylistType.RECOMMENDED));
        List<MusicDto> playedMusicDtoList = musicQueryRepository.getMusicArtistList(musicQueryRepository.findPlayedMusicList(id, PlaylistType.PLAYED));

        return new MainDto(user.getId(),
                user.getName(),
                user.getImagePath(),
                tagList,
                recommendMusicDtoList,
                playedMusicDtoList
        );
    }


    @Data
    @AllArgsConstructor
    private class UserDto {
        private String profileImage;
        private String userName;
        private List<String> tagList;
    }

    @Data
    @AllArgsConstructor
    private class MainDto {
        private Long userId;
        private String userName;
        private String userImage;
        private List<String> tagList;
        private List<MusicDto> recommendMusicList;
        private List<MusicDto> playedMusicList;

    }


}
