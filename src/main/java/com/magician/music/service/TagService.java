package com.magician.music.service;

import com.magician.music.api.TagApiController;
import com.magician.music.domain.Tag;
import com.magician.music.domain.TagType;
import com.magician.music.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    //태그 전체 조회
    @Transactional(readOnly = true)
    public List<Tag> findTags(){
        return tagRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tag findOne(Long id){
        return tagRepository.findOne(id);
    }

    public Long join(Tag tag){
        tagRepository.save(tag);
        return tag.getId();
    }

    public Tag findTagByName(String tagName){ return  tagRepository.findByName(tagName); }

    public List<String> getTagNameList() {
        return tagRepository.findAll().stream().map(
                tag -> tag.getName())
                .collect(Collectors.toList());
    }
}
