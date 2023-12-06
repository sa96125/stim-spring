package com.sa96125.stim.domain.notification.service.port;

import com.sa96125.stim.domain.notification.service.Notification;

public interface MessageQueueAdapter {
    void consumeCommentEvent(Notification notification);
}
