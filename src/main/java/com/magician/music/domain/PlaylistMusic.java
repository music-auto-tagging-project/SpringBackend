package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SequenceGenerator(
        name = "PLAYLIST_MUSIC_SEQ_GENERATOR",
        sequenceName = "PLAYLIST_MUSIC_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Getter @Setter
@Table(name = "playlist_has_music")
public class PlaylistMusic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYLIST_MUSIC_SEQ_GENERATOR")
    @Column(name = "playlist_music_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;


    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;


    public PlaylistMusic() {

    }

    public PlaylistMusic(Playlist playlist, Music music) {
        this.playlist = playlist;
        this.music = music;
    }
}
