package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "music_has_genre")
public class MusicGenre implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
