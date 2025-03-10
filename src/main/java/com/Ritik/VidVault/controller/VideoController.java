package com.Ritik.VidVault.controller;

import com.Ritik.VidVault.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
        String message = videoService.upload(file);
        return getMapRes(message, "Failed to upload video file");
    }


    @PostMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteVideo(@PathVariable String id) {
        String message = videoService.deleteVideo(id);
        return getMapRes(message, "Failed to deleted video");
    }


    // map response genertor
    private ResponseEntity<Map<String, String>> getMapRes(String msg, String errMsg) {
        Map<String, String> res = new HashMap<>();
        if (msg != null && !msg.equals("")) {
            res.put("message", msg);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.put("message", errMsg);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
