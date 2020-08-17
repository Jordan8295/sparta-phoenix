package com.spartaphoenix.spapp.models;

import java.util.Date;
import java.util.UUID;

public class Article {

    private final UUID id;
    private String header;
    private String preview;
    private String content;
    private String author;
    private Date timestamp;

    public Article(
            UUID id,
            String header,
            String preview,
            String content,
            String author
    ) {
        this.id = id;
        this.header = header;
        this.preview = preview;
        this.content = content;
        this.author = author;
        this.timestamp = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
