package com.sa96125.stim.domain.feed.repository;

import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.comment.repository.CommentEntity;
import com.sa96125.stim.domain.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "feeds")
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "feed_id", nullable = false)
    private String feedId;
    
    @Column
    private String title;
    
    @Column
    private String content;
    
    @Column
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
}
