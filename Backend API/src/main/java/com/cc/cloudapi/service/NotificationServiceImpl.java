package com.cc.cloudapi.service;

import com.cc.cloudapi.entity.NotificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anish on 23/01/2023
 * @project cloudapi
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final RestTemplate restTemplate;

    private static final String HOOKS_URL = "https://hooks.slack.com/services/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";

    @Autowired
    public NotificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendNotification(NotificationDto notificationDto){

        Map<String,String> messageBuilder = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();

        String message = ":rat: ALERT!!! "+notificationDto.getRatNumber()+" Detected with Confidence Level : "+notificationDto.getConfidenceLevel()+" at "
                + notificationDto.getCreatedDate()+". Check the website for more details. :smirk_cat:";
        headers.setContentType(MediaType.APPLICATION_JSON);
        messageBuilder.put("text",message);

        HttpEntity<Map<String,String>> request = new HttpEntity<>(messageBuilder, headers);
        restTemplate.postForEntity(HOOKS_URL,request, String.class);
    }
}
