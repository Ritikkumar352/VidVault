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
        if(filename!=null){
            return "Video successfully uploaded!";
        }


        return "uploaded";

    }

    public String deleteVideo(String id) {

        // TODO implement delete logic
        return "success:- Video deleted";
    }
}

