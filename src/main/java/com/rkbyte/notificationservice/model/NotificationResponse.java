package com.rkbyte.notificationservice.model;

import lombok.*;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private int status;
    private String message;
}
