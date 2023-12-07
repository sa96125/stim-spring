package com.sa96125.stim.domain.notification.connect;

import com.sa96125.stim.domain.notification.service.port.NotificationService;
import com.sa96125.stim.domain.notification.service.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class NotificationHandler implements WebSocketHandler {
    
    private final NotificationService notificationService;
    
    @MessageMapping("/notification")
    public Flux<Notification> getNotifications() {
        return notificationService.getNotificationStream();
    }
    
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return notificationService.getNotificationStream()
                .map(Notification::toWebSocketMessage)
                .flatMap(message -> session.send(Mono.just(session.textMessage(String.valueOf(message)))))
                .then();
    }
}
