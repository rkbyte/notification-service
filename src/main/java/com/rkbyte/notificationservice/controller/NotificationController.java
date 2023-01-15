package com.rkbyte.notificationservice.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.rkbyte.notificationservice.model.NotificationRequest;
import com.rkbyte.notificationservice.model.NotificationResponse;
import com.rkbyte.notificationservice.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    private NotificationService pushNotificationService;

    public NotificationController(NotificationService notificationService) {
        this.pushNotificationService = notificationService;
    }

    @PostMapping("/notification/topic-with-data")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        try {
            String response = pushNotificationService.sendPushNotificationWithData(request);
            return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(), response), HttpStatus.OK);
        } catch (FirebaseMessagingException e) {
            return new ResponseEntity<>(new NotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Oops! Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/notification/topic-without-data")
    public ResponseEntity<NotificationResponse> sendDataNotification(@RequestBody NotificationRequest request) {
        try {

            String response = pushNotificationService.sendPushNotificationWithoutData(request);
            return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(), response), HttpStatus.OK);
        } catch (FirebaseMessagingException e) {
            return new ResponseEntity<>(new NotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Oops! Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
