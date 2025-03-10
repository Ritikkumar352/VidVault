package com.Ritik.VidVault.controller;

import com.Ritik.VidVault.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity <Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
       String message= videoService.upload(file);
        Map<String,String> map=new HashMap<>();
        map.put("message",message);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity <Map<String, String>> deleteVideo(@PathVariable String id) {
        String message=videoService.deleteVideo(id);
        Map<String,String> map=new HashMap<>();
        map.put("message",message);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
