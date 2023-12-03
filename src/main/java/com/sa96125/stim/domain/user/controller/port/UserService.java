package com.sa96125.stim.domain.user.controller.port;

import com.sa96125.stim.domain.user.controller.RequestCreate;
import com.sa96125.stim.domain.user.controller.RequestUpdate;
import com.sa96125.stim.domain.user.service.User;

public interface UserService {
    User createUserBy(RequestCreate request);
    User updateUserBy(String id, RequestUpdate request);
    User deleteUserBy(String userId);
    User getUserBy(String userId);
}
