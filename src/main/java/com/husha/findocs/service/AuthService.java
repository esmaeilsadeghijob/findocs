package com.husha.findocs.service;

import com.husha.findocs.dto.AuthResponse;
import com.husha.findocs.dto.LoginRequest;
import com.husha.findocs.dto.RegisterRequest;
import com.husha.findocs.model.Role;
import com.husha.findocs.model.User;
import com.husha.findocs.repository.RoleRepository;
import com.husha.findocs.repository.UserRepository;
import com.husha.findocs.config.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);

        return new AuthResponse(token, user.getRole().getName());
    }

    public void register(RegisterRequest req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("کاربری با این نام وجود دارد");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("نقش USER یافت نشد"));

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFullName(req.getFullName());
        user.setCreatedAt(Instant.now());
        user.setRole(userRole);
        user.setApproved(false); // تایید توسط ادمین

        userRepository.save(user);
    }
}
