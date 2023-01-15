package com.rkbyte.notificationservice.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.rkbyte.notificationservice.model.NotificationRequest;

public interface NotificationService {
    String sendPushNotificationWithData(NotificationRequest request) throws FirebaseMessagingException;
    String sendPushNotificationWithoutData(NotificationRequest request) throws FirebaseMessagingException;
}
