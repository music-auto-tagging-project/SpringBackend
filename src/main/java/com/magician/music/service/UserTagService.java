package com.magician.music.service;

import com.magician.music.domain.TagType;
import com.magician.music.domain.UserTag;
import com.magician.music.repository.TagRepository;
import com.magician.music.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTagService {
    private final UserTagRepository userTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public void save(UserTag userTag){
        userTagRepository.save(userTag);
    }

    @Transactional(readOnly = true)
    public List<UserTag> findTagListByUserId(Long userId) {
        return userTagRepository.findAllByUserId(userId);
    }

    @Transactional
    public void updateUserTagTypeByUserIdAndTagId(Long userId, Long TagId, TagType tagType){
       UserTag userTag = userTagRepository.findOne(userId, TagId);
       userTag.setTagType(tagType);
    }

    @Transactional
    public void updateUserTagTypeByUserIdAndTagName(Long userId, String tagName, TagType tagType) {
        Long tagId = tagRepository.findByName(tagName).getId();
        UserTag userTag = userTagRepository.findOne(userId, tagId);
        userTag.setTagType(tagType);
    }

    @Transactional
    public UserTag findTagByUserIdAndTagName(Long userId, String tagName) {
        return userTagRepository.findByUserIdAndTagName(userId, tagName);
    }
}
