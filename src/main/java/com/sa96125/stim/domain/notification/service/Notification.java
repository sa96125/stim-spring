package com.sa96125.stim.domain.notification.service;

import com.sa96125.stim.common.api.type.Job;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.repository.FeedEntity;
import com.sa96125.stim.domain.notification.repository.NotificationEntity;
import com.sa96125.stim.domain.user.service.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Notification {

    private String notificationId;
    private String userId;
    private String message;
    private Status read;
    private Job job;

    public NotificationEntity toEntity() {
        NotificationEntity e = new NotificationEntity();
        e.setNotificationId(this.notificationId);
        e.setUserId(this.userId);
        e.setMessage(this.message);
        e.setRead(this.read);
        e.setJob(this.job);
        return e;
    }
}
