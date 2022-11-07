package com.magician.music.api;

import com.magician.music.domain.PlaylistType;
import com.magician.music.domain.TagType;
import com.magician.music.domain.User;
import com.magician.music.dto.PlaylistDto;
import com.magician.music.repository.query.MusicQueryDto;
import com.magician.music.repository.query.MusicQueryRepository;
import com.magician.music.service.PlaylistService;
import com.magician.music.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final MusicQueryRepository musicQueryRepository;
    private final PlaylistService playlistService;

    @GetMapping("user/info/{userId}")
    public UserDto getUserPageUserInfo(@PathVariable("userId") Long id) {

        User user = userService.findOne(id);

        /* user가 가지고 있는 tag name list */
        List<String> fixedTagList = new ArrayList<>();

        List<String> unfixedTagList = new ArrayList<>();

        user.getTagList().stream()
                .forEach( t-> {
                    if (t.getTagType()==TagType.FIXED){
                        fixedTagList.add(t.getTag().getName());
                    } else if (t.getTagType() == TagType.UNFIXED) {
                        unfixedTagList.add(t.getTag().getName());
                    }
                });


        return new UserDto(user.getImagePath(), user.getName(), fixedTagList, unfixedTagList, playlistService.getPlaylistListByUserId(user.getId()));
    }

    @GetMapping("main/{userId}")
    public MainDto getMainPageUserInfo(@PathVariable("userId") Long id) {
        User user = userService.findOne(id);

        /* user가 가지고 있는 tag name list */
        List<String> tagList = user.getTagList().stream()
                .map(t -> t.getTag().getName())
                .collect(Collectors.toList());


        List<MusicQueryDto> recommendMusicDtoList = musicQueryRepository
                .getMusicArtistList(musicQueryRepository
                        .findMusicListByPlaylistType(id, PlaylistType.RECOMMENDED));

        List<MusicQueryDto> playedMusicDtoList = musicQueryRepository
                .getMusicArtistList(musicQueryRepository
                        .findPlayedMusicList(id, PlaylistType.PLAYED));

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
        private List<String> fixedTagList;
        private List<String> unfixedTagList;
        private List<PlaylistDto> playlist;

    }

    @Data
    @AllArgsConstructor
    private class MainDto {
        private Long userId;
        private String userName;
        private String userImage;
        private List<String> tagList;
        private List<MusicQueryDto> recommendMusicList;
        private List<MusicQueryDto> playedMusicList;

    }


}
