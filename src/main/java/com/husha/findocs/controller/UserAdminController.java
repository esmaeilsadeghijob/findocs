package com.husha.findocs.controller;

import com.husha.findocs.model.User;
import com.husha.findocs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAdminController {

    private final UserRepository userRepository;

    @GetMapping("/pending")
    public List<User> listPendingUsers() {
        return userRepository.findByApprovedFalse();
    }

    @PostMapping("/{userId}/approve")
    public ResponseEntity<String> approveUser(@PathVariable UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setApproved(true);
        userRepository.save(user);
        return ResponseEntity.ok("کاربر تایید شد");
    }
}
