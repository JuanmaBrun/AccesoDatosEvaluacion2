package com.vedruna.twitterapi2ev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
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
    private List<Publication> publications;

    private List<User> followers;

    private List<User> followed;
}
