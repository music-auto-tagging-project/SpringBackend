package com.magician.music.service;

import com.magician.music.domain.Music;
import com.magician.music.repository.music.simpleRepository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;

    @Transactional(readOnly = true)
    public Music findMusic(Long id){
        return musicRepository.findOne(id);
    }


}
