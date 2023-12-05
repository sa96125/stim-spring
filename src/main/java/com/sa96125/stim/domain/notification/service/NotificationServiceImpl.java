package com.sa96125.stim.domain.notification.service;

import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import com.sa96125.stim.common.api.type.Job;
import com.sa96125.stim.domain.notification.connect.NotificationConnector;
import com.sa96125.stim.domain.notification.service.port.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationConnector {

    private final NotificationRepository notificationRepository;

    @Override
    public void send(String userId, Job job) {
        try {
            Notification notification = Notification.builder()
                    .notificationId(UUID.randomUUID().toString())
                    .userId(userId)
                    .message("새로운 알림이 있습니다.")
                    .job(job)
                    .build();

            notificationRepository.save(notification.toEntity());
        } catch (Exception e) {
            log.error("Failed to send notification: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to send notification with userId: " + userId);
        }
    }
}
