package com.sa96125.stim.domain.notification.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotificationJpaRepository extends CrudRepository<NotificationEntity, Long> {
    void deleteByNotificationId(String notificationId);
    Optional<NotificationEntity> findByNotificationId(String notificationId);
}
