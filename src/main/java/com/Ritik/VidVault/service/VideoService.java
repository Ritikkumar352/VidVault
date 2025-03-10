package com.Ritik.VidVault.service;

import com.Ritik.VidVault.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class VideoService {

    @Autowired
    private VideoRepo videoRepo;

    public String upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        // TODO implement upload logic
        String url="alkndnf";  // get fron gcp

        if(url!=null){
            return "Video successfully uploaded!";
        }
        return null;  // null--> "failed to perform operation"... handeled in controller
    }

    public String deleteVideo(String id) {
        // TODO implement delete logic

        String url="abc";
        if(url!=null){
            return "Video successfully deleted!";
        }
        return null;   // null--> failed to perform operation... handeled in controller
    }
}

