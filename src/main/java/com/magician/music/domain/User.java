package com.magician.music.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String pwd;

    private String name;

    private LocalDate birthday;

    private String phone;

    private String gender;

    private String nickname;

    @Column(name = "register_datetime")
    private LocalDateTime registerDateTime;

    @Column(name = "image_path")
    private String imagePath;


    @OneToMany(mappedBy = "user")
    private List<UserTag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Playlist> playlistList = new ArrayList<>();
}
