package com.cc.cloudapi.utils;

import com.cc.cloudapi.entity.Image;
import com.cc.cloudapi.entity.ImageDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anish on 12/11/2022
 * @project cloudapi
 */
public class ImageUtils {

    public static String localDateTimeToString(LocalDateTime date) {
        return date.toString();
    }

    public static LocalDateTime stringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
    }

    public static String[] splitString(String fileName, String regex) {
        return fileName.split(regex);
    }

    public static ImageDto convertEntityToDto(Image imageEntity) {
        return new ImageDto().builder()
                .id(imageEntity.getId())
                .name(imageEntity.getName())
                .type(imageEntity.getType())
                .image(imageEntity.getImage())
                .createdDate(localDateTimeToString(imageEntity.getCreatedDate()))
                .confidenceLevel(imageEntity.getConfidenceLevel())
                .status(imageEntity.getStatus())
                .build();
    }

    public static List<ImageDto> convertEntityToDtoList(List<Image> images) {
        return images.stream().map(image -> convertEntityToDto(image)).collect(Collectors.toList());
    }

    public static String splitTimeString(String fileName, String regex) {
        String[] split = fileName.split(regex);
        return split[0] + "." + split[1];
    }
}
