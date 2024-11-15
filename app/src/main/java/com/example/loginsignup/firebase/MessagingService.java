package com.example.loginsignup.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    /**
     * Called when a new token is generated for the device.
     *
     * @param token the newly generated token
     */
    @Override
    public void onNewToken(@NonNull String token){
        super.onNewToken(token);
        Log.d("FCM", "Token: " + token);
    }
    /**
     * Called when new message received.
     *
     * @param message the received message containing data and notification payloads
     */
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message){
        super.onMessageReceived(message);
        Log.d("FCM", "460 Message: " + message.getNotification().getBody());
    }
}
