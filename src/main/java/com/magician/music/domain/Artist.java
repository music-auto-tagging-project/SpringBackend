package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Artist {
    @Id @GeneratedValue
    @Column(name = "artist_id")
    private Long id;

    private String name;

    @Column(name = "image_path")
    private String imagePath;

    private String agency;

    @Column(name = "debut_date")
    private LocalDate debutDate;

    @OneToMany(mappedBy = "artist")
    private List<MusicArtist> musicList = new ArrayList<>();

}
