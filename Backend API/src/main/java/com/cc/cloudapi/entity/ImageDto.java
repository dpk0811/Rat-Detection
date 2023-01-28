package com.cc.cloudapi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author anish on 18/01/2023
 * @project cloudapi
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private Long id;

    @NotNull(message = "Image Name Cannot Be Null")
    @NotEmpty(message = "Image Name Cannot Be Empty")
    private String name;

    @NotNull(message = "Image Type Cannot Be Null")
    @NotEmpty(message = "Image Type Cannot Be Empty")
    private String type;

    @NotNull(message = "Image Cannot Be Null")
    @NotEmpty(message = "Image Cannot Be Empty")
    private byte[] image;

    @NotNull(message = "Date Cannot Be Null")
    @NotEmpty(message = "Date Cannot Be Empty")
    @DateTimeFormat
    private String createdDate;

    @NotNull(message = "Confidence Level cannot Be Null")
    private Double confidenceLevel;

    private Boolean status;
}
