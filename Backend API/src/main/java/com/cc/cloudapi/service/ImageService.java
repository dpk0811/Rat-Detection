package com.cc.cloudapi.service;

import com.cc.cloudapi.entity.Image;
import com.cc.cloudapi.entity.ImageDto;

import java.util.List;
import java.util.Optional;

/**
 * @author anish on 12/11/2022
 * @project cloudapi
 */
public interface ImageService {

    Optional<Image> getImageById(Long Id);

    List<Image> getAllImage();

    Long saveImage(Image image);
}
