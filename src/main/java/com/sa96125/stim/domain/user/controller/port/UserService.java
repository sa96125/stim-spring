package com.sa96125.stim.domain.user.controller.port;

import com.sa96125.stim.domain.user.service.User;

public interface UserService {
    User create(User request);
    
    User update(User request);
    
    User delete(String userId);
    
    User getById(String userId);
    
    User getByEmail(String userId);
}
