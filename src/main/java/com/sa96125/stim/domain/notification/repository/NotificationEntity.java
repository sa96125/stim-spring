package com.sa96125.stim.domain.notification.repository;

import com.sa96125.stim.common.api.type.Job;
import com.sa96125.stim.common.api.type.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_id", nullable = false)
    private String notificationId;

    @Column()
    private String userId;

    @Column()
    private String message;

    @Column()
    private Status read;

    @Column()
    private Job job;
}
