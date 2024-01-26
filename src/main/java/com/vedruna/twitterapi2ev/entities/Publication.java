package com.vedruna.twitterapi2ev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_id")
    private Long publicationId;

    @Column(name = "P_author")
    private User author;

    @Column(name = "P_text")
    private String text;

    @Column(name = "P_createDate")
    private Date createDate;

    @Column(name = "P_editDate")
    private Date editDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "P_FK_USER_ID", referencedColumnName = "U_userId")
    private User relateUser;
}
