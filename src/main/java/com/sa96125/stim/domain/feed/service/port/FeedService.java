package com.sa96125.stim.domain.feed.service.port;

import com.sa96125.stim.domain.feed.service.Feed;

public interface FeedService {
    Feed create(Feed feed);
    
    Feed update(Feed feed);
    
    Feed delete(String feedId);
    
    Feed getById(String feedId);
}
