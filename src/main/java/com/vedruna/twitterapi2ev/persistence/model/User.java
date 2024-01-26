package com.vedruna.twitterapi2ev.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_userId")
    private Long userId;

    @Column(name = "U_userName", unique = true)
    private String userName;

    @Column(name = "U_email", unique = true)
    private String email;

    @Column(name = "U_password")
    private String password;

    @Column(name = "U_description")
    private String description;

    @Column(name = "U_createDate")
    private Date createDate;

    @OneToMany(mappedBy = "relatedUser")
    @JoinColumn()
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    private List<User> followed;

}