package com.sa96125.stim.domain.comment.controller;

import com.sa96125.stim.domain.comment.service.port.CommentService;
import com.sa96125.stim.domain.comment.service.Comment;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 (comments)")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;
    
    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseComment> register(@RequestBody RequestCreate request) {
        Comment comment = commentService.create(request.toComment());
        return ResponseEntity.ok().body(ResponseComment.from(comment));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseComment> edit(@PathVariable String id, @RequestBody RequestUpdate request) {
        Comment comment = commentService.update(request.toComment(id));
        return ResponseEntity.ok().body(ResponseComment.from(comment));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseComment> remove(@PathVariable String id) {
        Comment comment = commentService.delete(id);
        return ResponseEntity.ok().body(ResponseComment.from(comment));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseComment> detail(@PathVariable String id) {
        Comment comment = commentService.getById(id);
        return ResponseEntity.ok().body(ResponseComment.from(comment));
    }
}
