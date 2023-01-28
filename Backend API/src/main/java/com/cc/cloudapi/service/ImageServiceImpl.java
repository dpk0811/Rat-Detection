package com.cc.cloudapi.service;

import com.cc.cloudapi.entity.Image;
import com.cc.cloudapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author anish on 12/11/2022
 * @project cloudapi
 */
@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> getAllImage() {
        return imageRepository.getAllImage();
    }

    @Override
    public Long saveImage(Image image) {
       return imageRepository.save(image).getId();
    }
}
