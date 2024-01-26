package com.vedruna.twitterapi2ev.dto;

import com.vedruna.twitterapi2ev.persistence.model.User;
import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostDTO implements Serializable {
    private Long publicationId;
    private User author;
    private String text;

    public PostDTO(Long publicationId, User author, String text) {
        this.publicationId = publicationId;
        this.author = author;
        this.text = text;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
