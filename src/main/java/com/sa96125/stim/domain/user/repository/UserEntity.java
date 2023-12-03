package com.sa96125.stim.domain.user.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
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
    
    @Column
    private String role;
    
    @Column
    private String status;
    
    @Column
    private long lastLoginAt;
}
