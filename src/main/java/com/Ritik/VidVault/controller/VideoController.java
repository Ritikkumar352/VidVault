package com.Ritik.VidVault.controller;

import com.Ritik.VidVault.entity.Video;
import com.Ritik.VidVault.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/")
    public String home(){
        return "app running";
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        String contentType = file.getContentType();
        String message = videoService.upload(file);
        System.out.println(contentType );
        if (contentType == null || !contentType.startsWith("video/")) {
            return getMapRes(null,"invalid file type..Upload Video File",HttpStatus.BAD_REQUEST);
        }
        return getMapRes(message, "Video uploaded ",HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteVideo2(@PathVariable long id) {
        String message = videoService.deleteVideo2(id);
        return getMapRes(message, "Failed to deleted video",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/videos")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }


    // map response genertor
    private ResponseEntity<Map<String, String>> getMapRes(String msg, String errMsg,HttpStatus status) {
        Map<String, String> res = new HashMap<>();
        if (msg != null && !msg.equals("")) {
            res.put("message", msg);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.put("message", errMsg);
            return new ResponseEntity<>(res, status);
        }
    }

}
