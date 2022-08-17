package com.magician.music.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "tag_has_music")
public class MusicTag implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
