package com.cc.cloudapi.entity;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author anish on 23/01/2023
 * @project cloudapi
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private String ratNumber;

    private String confidenceLevel;

    private String createdDate;
}
