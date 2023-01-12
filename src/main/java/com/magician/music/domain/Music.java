package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Music {
    @Id @GeneratedValue
    @Column(name = "music_id")
    private Long id;
    private String title;
    private String lyric;

    @Column(name = "play_time")
    private LocalTime playTime;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "youtube_id")
    private String youtubeId;

    @OneToMany(mappedBy = "music")
    private List<MusicArtist> artistList = new ArrayList<>();

    @OneToMany(mappedBy = "music")
    private List<MusicGenre> genreList = new ArrayList<>();

    @OneToMany(mappedBy = "music")
    private List<MusicTag> tagList = new ArrayList<>();


}
