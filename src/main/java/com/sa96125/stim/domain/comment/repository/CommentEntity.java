package com.sa96125.stim.domain.comment.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.repository.FeedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comments")
@JsonIgnoreProperties({"parentComment", "childComments"})
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "comment_id", nullable = false)
    private String commentId;
    
    @Column(nullable = false)
    private String content;
    
    @Column(nullable = false)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "feed_id", referencedColumnName = "id")
    private FeedEntity feed;
    
    @ManyToOne
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "id")
    private CommentEntity parentComment;
    
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<CommentEntity> childComments;
}
