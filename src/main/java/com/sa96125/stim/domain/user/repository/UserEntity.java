package com.sa96125.stim.domain.user.repository;

import com.sa96125.stim.common.api.type.Role;
import com.sa96125.stim.common.api.type.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userId;
    
    @Column
    private String email;
    
    @Column
    private String password;
    
    @Column
    private String nick;
    
    @Column
    private String name;
    
    @Column
    private String mobile;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column
    private long lastLoginAt;
}
