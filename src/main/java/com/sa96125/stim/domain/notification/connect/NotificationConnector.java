package com.sa96125.stim.domain.notification.connect;

import com.sa96125.stim.common.api.type.Job;

public interface NotificationConnector {
    void send(String userId, Job job);
}
