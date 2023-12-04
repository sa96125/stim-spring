package com.sa96125.stim.domain.feed.repository;

import com.sa96125.stim.common.api.type.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feeds")
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String feedId;
    
    @Column(nullable = false)
    private String userId;
    
    @Column
    private String title;
    
    @Column
    private String content;
    
    @Column
    private Status status;
}
