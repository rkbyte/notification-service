package com.rkbyte.notificationservice.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class FirebaseInitializer {
    private String firebaseConfigPath;
    public Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    public FirebaseInitializer(@Value("${firebase.configuration-filepath}") String firebaseConfigPath) {
        this.firebaseConfigPath = firebaseConfigPath;
    }

    @PostConstruct
    public void initialize() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(googleCredentials)
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
