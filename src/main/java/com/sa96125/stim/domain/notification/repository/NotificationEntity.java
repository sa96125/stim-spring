package com.sa96125.stim.domain.notification.repository;

import com.sa96125.stim.common.api.type.Notify;
import com.sa96125.stim.domain.user.repository.UserEntity;
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
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity targetUser;
    
    @Column()
    private String message;
    
    @Column()
    private String isRead;
    
    @Column()
    private Notify job;
}
