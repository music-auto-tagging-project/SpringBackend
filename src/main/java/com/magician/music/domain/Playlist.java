package com.magician.music.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Playlist {
    @Id @GeneratedValue
    @Column(name = "playlist_id")
    private Long id;

    @Column(name = "playlist_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PlaylistType playlistType;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistMusic> playlistMusicList;

}
