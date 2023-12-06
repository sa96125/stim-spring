package com.sa96125.stim.common.kafka;

import com.sa96125.stim.common.security.SecurityContextProvider;
import com.sa96125.stim.domain.notification.service.Notification;
import com.sa96125.stim.domain.notification.service.port.MessageQueueAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class KafkaClient implements MessageQueueAdapter {
    
    private final KafkaTemplate<String, Notification> kafkaTemplate;
    private final SecurityContextProvider securityContextProvider;
    
    public void send(String topic, Notification notification) {
        kafkaTemplate.send(topic, notification);
    }
    
    @KafkaListener(topics = "comment-topic", groupId = "comment-group")
    public void consumeCommentEvent(Notification notification) {
        if (Objects.equals(securityContextProvider.authenticateUser(), notification.getTargetUser().getEmail())) {
            send("comment-topic", notification);
        }
    }
}
