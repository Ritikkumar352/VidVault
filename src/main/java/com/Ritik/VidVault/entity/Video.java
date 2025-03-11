package com.Ritik.VidVault.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String url;
    private String downlaodUrl; // check update in table
    @Column(name = "uploadedAt")
    private LocalDateTime uploadedAt;
    private long size;
    private String format;

    // first complete these ...above
//    private int duration;   // TODO -- format to HH:MM:SS to displayadd  , it's in sec ig or ms
//    private String description;  // will be provided by user

    @PrePersist   // this will execute before inserting entity into db... use @PreUpdate for modification
    protected void onCreate() {
        uploadedAt = LocalDateTime.now();
    }


}
