package com.husha.findocs.controller;

import com.husha.findocs.dto.AuthResponse;
import com.husha.findocs.dto.LoginRequest;
import com.husha.findocs.dto.RegisterRequest;
import com.husha.findocs.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("درخواست ثبت‌نام با موفقیت ارسال شد؛ منتظر تأیید ادمین باشید");
    }

}
