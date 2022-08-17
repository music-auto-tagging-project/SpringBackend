package com.magician.music.repository.music.simpleRepository;

import com.magician.music.domain.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MusicRepository {
    private final EntityManager em;

    public Music findOne(Long id){
        return em.find(Music.class, id);
    }
}
