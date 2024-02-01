package com.vedruna.twitterapi2ev.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "publication")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_id")
    private Long publicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "P_author_username", referencedColumnName = "U_username")
    private User author;

    @Column(name = "P_text")
    private String text;

    @Column(name = "P_createDate")
    private LocalDate createDate;

    @Column(name = "P_editDate")
    private LocalDate editDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "P_FK_USER_ID", referencedColumnName = "U_userId")
    private User relateUser;
}
