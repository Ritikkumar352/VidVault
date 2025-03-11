package com.Ritik.VidVault.service;

import com.Ritik.VidVault.entity.Video;
import com.Ritik.VidVault.repository.VideoRepo;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoService {

    @Autowired
    private VideoRepo videoRepo;

    @Autowired
    private Storage storage;

    private final String bucketName = "videoupload352";

    public String upload(MultipartFile file) {
        // TODO implement upload logic
        // TODO add check for upload file video only... if required
        String fileURL = null;
        Blob blob = null;

        // get details from the uploaded file-- offline file
        long size = file.getSize();  // in bytes or bits ? check
        String userFileName = file.getOriginalFilename();
        String type = "unknown file type";
        if (userFileName != null) {
            type = userFileName.substring(userFileName.lastIndexOf(".") + 1);
            System.out.println(type + " -- file type");
        } else {
            System.out.println("file not uploaded!!--> no name ");
        }

        // upload logic
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String contentType = file.getContentType();

            // blob

            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
            blob = storage.create(blobInfo, file.getBytes());  // upload

//            file  url
            fileURL = "https://storage.googleapis.com/" + bucketName + "/" + fileName;
            System.out.println(fileURL);
            System.out.println("blob.getNmae()-> " + blob.getName() + " all info in blob ,, of uploaded file " + blob.getMediaLink() + " - media link from blob obj " + blob.getContentType() + " - content type" + blob.getSize() + " - size of uploaded file");
            // !!!! this link .getMediaLink is directly downloading file !!!!!!!!!!!!!!!.. and fileURL is to watch ... use these to add download feature using download btn
            // use blob.getSize() to show size of video
            System.out.println(contentType);
//            return "Video Uploaded Successfully!! URL ->>  "+ fileURL;

        } catch (IOException e) {
            System.out.println("Error while uploading -->>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

        // TODO -> save these info in postgreSQL
        // if upload successfull now save to sql db

        Video saved = saveVideo(blob);
        if (saved == null) {
            System.out.println("save to sql db failed");
            return null;
        }

        return "video Uploaded and saved to sql db !! URL to wathc  ->> " + fileURL + " url to download " + blob.getMediaLink();

//        return (fileURL != null) ? "video uploded" : null;
        // null--> "failed to perform operation"... handeled in controller
    }

    public Video saveVideo(Blob blob) {
        Video video = new Video();
//        long id  =  ?? -- use blobId to del
        String title = blob.getName();
        String watchUrl = "https://storage.googleapis.com/" + bucketName + "/" + title;
        String downloadUrl = blob.getMediaLink();
        String name = blob.getName();   // uploaded File name
        long size = (blob.getSize()) / 1024; //--> in kb
        String format = blob.getContentType();
//       String duration

        video.setTitle(title);
        video.setUrl(watchUrl);
        video.setDownlaodUrl(downloadUrl);
        video.setSize(size);
        video.setFormat(format);

        /* Optional ? */
        Video savedVdData = null;
        try {
            savedVdData = videoRepo.save(video);

        } catch (Exception e) {
            System.out.println("Error while saving video to sql db" + e.getMessage());
        }

        return savedVdData;

    }

    public String deleteVideo(long id) {
        // TODO implement delete logic

        Video delVideo = videoRepo.getById(id);  // use findById
        String url = delVideo.getUrl();

        BlobId blobId = BlobId.of(bucketName, url);
        System.out.println(blobId + " <--bloadid--");
        System.out.println(blobId.getName());
//        boolean res = storage.delete(BlobId.of(bucketName, url));
        boolean res = storage.delete(blobId.getName());

//        if(res){
//            return "Video deleted ...";
//        }
//        return null;
        return (res) ? "video deleted.." : null;
//        String url = "abc";
//        if (url != null) {
//            return "Video successfully deleted!";
//        }
//        return null;   // null--> failed to perform operation... handeled in controller
    }

    public String deleteVideo2(long id) {
        Optional<Video> delVideo = videoRepo.findById(id);
        boolean res = false;
        if (delVideo.isPresent()) {
            Video video = delVideo.get();
            String title = video.getTitle(); // title-- obj name
//            String url = video.getUrl();
//            System.out.println("trying to delete");
//            System.out.println("url->> " + url);
//            System.out.println("title->> " + title);
            res = storage.delete(bucketName, title);
        }

        // TODO -- deleted from clloud now delete that video data from db also

        if(res){
            String dbDelete=deleteFromDb(id);
        }

        return (res) ? "video deleted.." : null;
    }

    public String deleteFromDb(long id) {
        videoRepo.deleteById(id);
        return "video deleted.. from db";
    }



}

