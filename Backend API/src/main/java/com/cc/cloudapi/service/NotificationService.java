package com.cc.cloudapi.service;

import com.cc.cloudapi.entity.NotificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author anish on 23/01/2023
 * @project cloudapi
 */
public interface NotificationService {

    public void sendNotification(NotificationDto notificationDto);
}
