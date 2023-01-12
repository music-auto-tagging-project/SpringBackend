package com.magician.music.repository;

import com.magician.music.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("v2.MusicRepository")
public interface MusicRepository extends JpaRepository<Music, Long> {
}
