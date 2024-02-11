package com.vedruna.twitterapi2ev.dto;

import com.vedruna.twitterapi2ev.persistence.model.Post;

public class PostDTO {

    private Long publicationId;
    private String author;
    private String text;

    public PostDTO() {}

    public PostDTO(Post post) {
        this.publicationId = post.getId();
        this.author = post.getAuthor().getUsername();
        this.text = post.getText();
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
