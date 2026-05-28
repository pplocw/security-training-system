package com.qclaw.securitytraining.service;
import com.qclaw.securitytraining.config.JwtUtils;
import com.qclaw.securitytraining.dto.*;
import com.qclaw.securitytraining.entity.User;
import com.qclaw.securitytraining.mapper.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    public LoginResponse login(LoginRequest req) {
        User u = userRepo.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("用户不存在"));
        if (!encoder.matches(req.getPassword(), u.getPassword())) throw new RuntimeException("密码错误");
        LoginResponse r = new LoginResponse();
        r.setToken(jwtUtils.generateToken(u.getId(), u.getUsername(), u.getRole()));
        r.setUserId(u.getId()); r.setUsername(u.getUsername());
        r.setNickname(u.getNickname()); r.setRole(u.getRole());
        return r;
    }
}