package com.sa96125.stim.domain.notification.service;

import com.sa96125.stim.common.api.type.Notify;
import com.sa96125.stim.domain.comment.service.Comment;
import com.sa96125.stim.domain.notification.service.port.NotificationService;
import com.sa96125.stim.common.kafka.port.MessageQueueAdapter;
import com.sa96125.stim.domain.notification.repository.port.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
    
    private final Flux<Notification> notificationFlux;
    private final MessageQueueAdapter messageQueueAdapter;
    private final NotificationRepository notificationRepository;
    
    @Autowired
    public NotificationServiceImpl(MessageQueueAdapter messageQueueAdapter, NotificationRepository notificationRepository) {
        Sinks.Many<Notification> notificationSink = Sinks.many().multicast().onBackpressureBuffer();
        this.notificationFlux = notificationSink.asFlux().share();
        this.messageQueueAdapter = messageQueueAdapter;
        this.notificationRepository = notificationRepository;
    }
    
    @Override
    public Flux<Notification> getNotificationStream() {
        return notificationFlux;
    }
    
    public void buildNotificationFromComment(Comment comment) {
        Notification notification = Notification.builder()
                .notificationId(UUID.randomUUID().toString())
                .targetUser(comment.getFeed().getUser())
                .message("New Comment.")
                .job(Notify.NEW_COMMENT)
                .isRead("N")
                .build();
        
        messageQueueAdapter.consumeCommentEvent(notification);
        notificationRepository.save(notification.toEntity());
    }
    
}
