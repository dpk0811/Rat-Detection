package com.cc.cloudapi.repository;

import com.cc.cloudapi.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author anish on 12/11/2022
 * @project cloudapi
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findById(Long id);

    @Query(value = "SELECT * FROM cloud_image",nativeQuery = true)
    List<Image> getAllImage();
}
