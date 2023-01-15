package com.rkbyte.notificationservice.service;

import com.google.firebase.messaging.*;
import com.rkbyte.notificationservice.model.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FirebaseNotificationService implements NotificationService {

    @Override
    public String sendPushNotificationWithData(NotificationRequest request) throws FirebaseMessagingException {
        Message message = getMessageWithData(request);
        return sendAndGetResponse(message);
    }

    @Override
    public String sendPushNotificationWithoutData(NotificationRequest request) throws FirebaseMessagingException {
        Message message = getMessageWithoutData(request);
        return sendAndGetResponse(message);
    }

    private String sendAndGetResponse(Message message) throws FirebaseMessagingException {
        return FirebaseMessaging.getInstance().send(message);
    }

    private Message getMessageWithData(NotificationRequest request) {
        return getMessageBuilder(request)
                .putAllData(request.getData())
                .build();
    }

    private Message getMessageWithoutData(NotificationRequest request) {
        return getMessageBuilder(request).build();
    }

    private Message.Builder getMessageBuilder(NotificationRequest request) {
        return Message.builder().
                setNotification(Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getMessage())
                        .build()
                )
                .setTopic(request.getTopic());
    }
}
