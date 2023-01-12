package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Album {
    @Id
    @Column(name = "album_id")
    private Long id;

    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "album")
    private List<Music> musicList = new ArrayList<>();
}
