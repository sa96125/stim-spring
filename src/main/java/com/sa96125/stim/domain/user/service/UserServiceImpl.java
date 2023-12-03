package com.sa96125.stim.domain.user.service;

import com.sa96125.stim.common.api.type.UserStatus;
import com.sa96125.stim.domain.user.controller.RequestCreate;
import com.sa96125.stim.domain.user.controller.RequestUpdate;
import com.sa96125.stim.domain.user.controller.port.UserService;
import com.sa96125.stim.domain.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    
    @Override
    public User createUserBy(RequestCreate request) {
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .email(request.getEmail())
                .password(request.getPassword())
                .status(UserStatus.PENDING.getValue())
                .build();
        
        return User.from(userRepository.save(user));
    }
    
    @Override
    public User updateUserBy(String userId, RequestUpdate request) {
        return null;
    }
    
    @Override
    public User deleteUserBy(String userId) {
        return null;
    }
    
    @Override
    public User getUserBy(String userId) {
        return null;
    }
}
