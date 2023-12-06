package com.sa96125.stim.domain.notification.service;

import com.sa96125.stim.common.api.type.Notify;
import com.sa96125.stim.domain.notification.repository.NotificationEntity;
import com.sa96125.stim.domain.user.service.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

@Getter
@Setter
@Builder
public class Notification {
    
    private String notificationId;
    private User targetUser;
    private String message;
    private String isRead;
    private Notify job;
    
    public NotificationEntity toEntity() {
        NotificationEntity e = new NotificationEntity();
        e.setNotificationId(this.notificationId);
        e.setTargetUser(this.targetUser.toEntity());
        e.setMessage(this.message);
        e.setIsRead(this.isRead);
        e.setJob(this.job);
        return e;
    }
    
    public WebSocketMessage<String> toWebSocketMessage() {
        String messagePayload = String.format("{\"notificationId\": \"%s\", \"isRead\": %s}", this.notificationId, this.isRead);
        return new TextMessage(messagePayload);
    }
}
