package com.sa96125.stim.domain.feed.controller;

import com.sa96125.stim.domain.feed.controller.port.FeedService;
import com.sa96125.stim.domain.feed.service.Feed;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시글 (feeds)")
@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {
    
    private final FeedService feedService;
    
    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseFeed> register(@RequestBody RequestCreate request) {
        Feed feed = feedService.create(request.toFeed());
        return ResponseEntity.ok().body(ResponseFeed.from(feed));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseFeed> edit(@PathVariable String id, @RequestBody RequestUpdate request) {
        Feed feed = feedService.update(request.toFeed(id));
        return ResponseEntity.ok().body(ResponseFeed.from(feed));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseFeed> remove(@PathVariable String id) {
        Feed feed = feedService.delete(id);
        return ResponseEntity.ok().body(ResponseFeed.from(feed));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseFeed> detail(@PathVariable String id) {
        Feed feed = feedService.getById(id);
        return ResponseEntity.ok().body(ResponseFeed.from(feed));
    }
}
