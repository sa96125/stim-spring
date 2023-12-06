package com.sa96125.stim.domain.user.controller;

import com.sa96125.stim.domain.user.service.port.UserService;
import com.sa96125.stim.domain.user.service.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 (users)")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping()
    public ResponseEntity<ResponseUser> register(@RequestBody RequestCreate request) {
        User user = userService.create(request.toUser());
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
    
    @PutMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseUser> edit(@RequestBody RequestUpdate request) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.update(request.toUser(id));
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
    
    @DeleteMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseUser> remove() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.delete(id);
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> detail(@PathVariable String id) {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
}
