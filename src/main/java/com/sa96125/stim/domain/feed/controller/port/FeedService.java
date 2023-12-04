package com.sa96125.stim.domain.feed.controller.port;

import com.sa96125.stim.domain.feed.service.Feed;

public interface FeedService {
    Feed create(Feed request);
    
    Feed update(Feed request);
    
    Feed delete(String feedId);
    
    Feed getById(String feedId);
}
