package com.sa96125.stim.common.flux;

import com.sa96125.stim.domain.notification.controller.NotificationController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfig {
    
    @Bean
    public HandlerMapping webSocketMapping(NotificationController notificationController) {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/notifications", notificationController);
        
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(map);
        mapping.setOrder(-1);
        return mapping;
    }
}
