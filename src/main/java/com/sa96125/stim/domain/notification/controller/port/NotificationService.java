package com.sa96125.stim.domain.notification.controller.port;

import com.sa96125.stim.domain.notification.service.Notification;
import reactor.core.publisher.Flux;

public interface NotificationService {
    Flux<Notification> getNotificationStream();
}
