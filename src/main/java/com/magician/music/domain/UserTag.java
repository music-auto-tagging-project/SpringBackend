package com.magician.music.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_has_tag")
@Getter @Setter
@AllArgsConstructor
public class UserTag implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TagType tagType;

    public UserTag() {

    }
}
