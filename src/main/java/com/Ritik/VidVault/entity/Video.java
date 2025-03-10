package com.Ritik.VidVault.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String url;

    @Column(name = "uploadedAt")
    private LocalDateTime uploadedAt;
    // first complete these ...above
    private int duration;   // TODO -- format to HH:MM:SS to displayadd  , it's in sec ig
    private int size;
    private String format;

    @PrePersist   // this will execute before inserting entity into db... use @PreUpdate for modification
    protected void onCreate() {
        uploadedAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
