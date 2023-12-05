package com.sa96125.stim.domain.notification.service.port;

import com.sa96125.stim.domain.notification.repository.NotificationEntity;

public interface NotificationRepository {
    void save(NotificationEntity notificationEntity);
}
