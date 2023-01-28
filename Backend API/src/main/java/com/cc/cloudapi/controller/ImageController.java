package com.cc.cloudapi.controller;

import com.cc.cloudapi.entity.Image;
import com.cc.cloudapi.entity.ImageDto;
import com.cc.cloudapi.entity.NotificationDto;
import com.cc.cloudapi.service.ImageService;
import com.cc.cloudapi.service.NotificationService;
import com.cc.cloudapi.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author anish on 12/11/2022
 * @project cloudapi
 */
@Slf4j
@RestController
@RequestMapping(value = "/image")
public class ImageController {

    private ImageService imageService;
    private NotificationService notificationService;

    @Autowired
    public ImageController(ImageService imageService, NotificationService notificationService) {
        this.imageService = imageService;
        this.notificationService = notificationService;
    }

    @GetMapping("/uploadTest")
    public String getImage() {
        return "Application Test API";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        String[] fileNames = ImageUtils.splitString(file.getOriginalFilename(), "_");

        Long imageId = imageService.saveImage(Image.builder()
                .name("Image" + fileNames[2])
                .type(file.getContentType())
                .confidenceLevel(Double.parseDouble(fileNames[1]))
                .image(file.getBytes())
                .createdDate(ImageUtils.stringToLocalDateTime(ImageUtils.splitTimeString(fileNames[2], "\\.")))
                .status(Boolean.FALSE)
                .build());

        log.info("Image " + imageId + " Saved Successfully  !!");

        try {
            notificationService.sendNotification(new NotificationDto(fileNames[0], fileNames[1], ImageUtils.splitTimeString(fileNames[2], "\\.")));
            log.info("Notification Sent for Image:" + imageId + " !!");
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("Notification Failure !!");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Image Uploaded Successfully :" + imageId);
    }

    @GetMapping("/list")
    public List<ImageDto> getAllImageDetails() {

        List<ImageDto> result;
        try {
            result = ImageUtils.convertEntityToDtoList(imageService.getAllImage());
            log.info("Images Information Fetched Successfully  !!");
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Images Information Cannot Be Fetched !!");
            return null;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {

        final Optional<Image> image = imageService.getImageById(id);
        try {
            ResponseEntity<byte[]> result = ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(image.get().getType()))
                    .body(image.get().getImage());
            log.info("Image " + image.get().getId() + " Fetched Successfully  !!");
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Image " + image.get().getId() + " Cannot Be Fetched !!");
            return null;
        }
    }
}
