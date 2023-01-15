package com.rkbyte.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class NotificationRequest {
    private String title;
    private String message;
    private String topic;
    private Map<String, String> data;
}
