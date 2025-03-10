package com.Ritik.VidVault.repository;

import com.Ritik.VidVault.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repo extends JpaRepository<Video, Long> {
}
