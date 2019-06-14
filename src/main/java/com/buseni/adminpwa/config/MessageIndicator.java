package com.buseni.adminpwa.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.buseni.adminpwa.messages.MessageRepo;
import com.buseni.adminpwa.messages.NotificationType;

@Component
public class MessageIndicator implements InfoContributor {
 
    @Autowired
    MessageRepo messageRepo;
 
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> userDetails = new HashMap<>();
        userDetails.put("push", messageRepo.countByNotificationType(NotificationType.PUSH));
        userDetails.put("sms", messageRepo.countByNotificationType(NotificationType.SMS));
 
        builder.withDetail("messages", userDetails);
    }
}