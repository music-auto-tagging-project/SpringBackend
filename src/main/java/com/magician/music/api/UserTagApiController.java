package com.magician.music.api;

import com.magician.music.domain.TagType;
import com.magician.music.domain.UserTag;
import com.magician.music.service.TagService;
import com.magician.music.service.UserService;
import com.magician.music.service.UserTagService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserTagApiController {

    private final UserTagService userTagService;
    private final UserService userService;
    private final TagService tagService;

    @PostMapping("user/tag")
    public void setUserTag(@RequestBody @Validated CreateTagRequest request) {
        /* 유저의 원래 태그 받아오기 */

        /* 변경된 태그 목록 받아오기 */
        List<TagFixedDto> modifiedFixedTagList = request.getUserTagList().stream()
                .filter(t -> t.getIsFixed() == TagType.FIXED)
                .collect(Collectors.toList());

        List<TagFixedDto> modifiedUnFixedTagList = request.getUserTagList().stream()
                .filter(t -> t.getIsFixed() == TagType.UNFIXED)
                .collect(Collectors.toList());

        /* 원래 목록과 비교하여 삭제된 태그, 고정된 태그 기록하기 */
        // Delete 된 태그 = 원래 있던 태그가 Fixed, UnFixed에서 없어짐
        // Fixed, UnFixed는 수정된 그대로 삽입.
        // 대신 Deleted된 태그에서 발견되면 이를 수정

        // 원래있던 태그들 다 delete로 변경한 다음 새로이 들어온 태그들을
        // fixed, unfixed로 update 해주면
        // 위 알고리즘 성립

        //delete로 변경
        userTagService.findTagListByUserId(request.getUserId())
                .forEach(ut ->
                        userTagService.updateUserTagTypeByUserIdAndTagId
                                (request.getUserId(), ut.getTag().getId(), TagType.DELETED));

        modifiedFixedTagList.forEach(tagFixedDto -> {
            if (userTagService.findTagByUserIdAndTagName(request.getUserId(), tagFixedDto.getTagName()) != null) {
                userTagService.updateUserTagTypeByUserIdAndTagName(request.getUserId(), tagFixedDto.getTagName(), TagType.FIXED);
            } else {
                userTagService.save(
                        new UserTag(
                                userService.findOne(request.getUserId()),
                                tagService.findTagByName(tagFixedDto.getTagName())
                                , tagFixedDto.getIsFixed()));
            }
        });


        modifiedUnFixedTagList.forEach(tagFixedDto -> {
            if (userTagService.findTagByUserIdAndTagName(request.getUserId(), tagFixedDto.getTagName()) != null) {
                userTagService.updateUserTagTypeByUserIdAndTagName(request.getUserId(), tagFixedDto.getTagName(), TagType.UNFIXED);
            } else {
                userTagService.save(
                        new UserTag(
                                userService.findOne(request.getUserId()),
                                tagService.findTagByName(tagFixedDto.getTagName())
                                , tagFixedDto.getIsFixed()));
            }
        });


    }

    @Data
    @Getter
    @AllArgsConstructor
    private static class CreateTagRequest {
        private Long userId;
        private List<TagFixedDto> userTagList;
    }

    @Data
    @Getter
    @AllArgsConstructor
    private static class TagFixedDto {
        private String tagName;
        private TagType isFixed;
    }
}
