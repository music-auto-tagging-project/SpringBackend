package com.magician.music.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@SequenceGenerator(
        name = "PLAYLIST_SEQ_GENERATOR",
        sequenceName = "PLAYLIST_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Builder
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYLIST_SEQ_GENERATOR")
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

    public Playlist() {

    }
}
