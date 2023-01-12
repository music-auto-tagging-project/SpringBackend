package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Genre {
    @Id @GeneratedValue
    @Column(name = "genre_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private List<MusicGenre> musicList =new ArrayList<>();
}
