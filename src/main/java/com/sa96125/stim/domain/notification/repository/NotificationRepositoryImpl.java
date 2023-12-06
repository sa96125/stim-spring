package com.sa96125.stim.domain.notification.repository;

import com.sa96125.stim.domain.notification.repository.port.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {
    
    private final NotificationJpaRepository notificationJpaRepository;
    
    @Override
    public void save(NotificationEntity notificationEntity) {
        notificationJpaRepository.save(notificationEntity);
    }
}
