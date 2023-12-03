package com.sa96125.stim.domain.user.controller;

import com.sa96125.stim.domain.user.controller.port.UserService;
import com.sa96125.stim.domain.user.service.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 (users)")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ResponseUser> register(@RequestBody RequestCreate request) {
        User user = userService.createUserBy(request);
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUser> edit(@PathVariable String id, @RequestBody RequestUpdate request) {
        User user = userService.updateUserBy(id, request);
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUser> remove(@PathVariable String id) {
        User user = userService.deleteUserBy(id);
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> detail(@PathVariable String id) {
        User user = userService.getUserBy(id);
        return ResponseEntity.ok().body(ResponseUser.from(user));
    }
    
}
