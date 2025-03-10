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
    @Column(name = "uploadedAt")
    private String url;
    private LocalDateTime uploadedAt;
    // first complete these ...above
    private int duration;   // TODO -- format to HH:MM:SS to display
    private int size;
    private String format;

    @PrePersist   // this will execute before inserting entity into db... use @PreUpdate for modification
    protected void onCreate() {
        uploadedAt = LocalDateTime.now();
    }




}
