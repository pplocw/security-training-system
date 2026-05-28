package com.qclaw.securitytraining.controller;
import com.qclaw.securitytraining.dto.*;
import com.qclaw.securitytraining.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest req) {
        try { return ApiResponse.success(authService.login(req)); }
        catch (Exception e) { return ApiResponse.error(e.getMessage()); }
    }
}