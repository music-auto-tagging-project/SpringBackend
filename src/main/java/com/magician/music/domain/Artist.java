package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

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

}
