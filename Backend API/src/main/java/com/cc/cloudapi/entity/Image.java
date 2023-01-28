package com.cc.cloudapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author anish on 12/11/2022
 * @project cloudapi
 */
@Entity
@Table(name="CLOUD_IMAGE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "confidence_level")
    private Double confidenceLevel;

    @CreatedDate
    @Column(name = "created_date",nullable = false)
    private LocalDateTime createdDate;
}