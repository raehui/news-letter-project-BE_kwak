package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.admin.AdminRegisterRequestDto;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.repository.AdminUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminAuthService {

    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.invite-code}")
    private String adminInviteCode;

    public void register(AdminRegisterRequestDto adminRegisterRequestDto) {

        String reqCode = adminRegisterRequestDto.getInviteCode() == null ? "" : adminRegisterRequestDto.getInviteCode().trim();
        String serverCode = adminInviteCode == null ? "" : adminInviteCode.trim();

        // 1. 초대코드 검증
        if (!serverCode.equals(reqCode)) {
            throw new IllegalArgumentException("관리자 가입 코드가 올바르지 않습니다.");
        }
        // 2. 이메일 중복 체크
        if (adminUserRepository.existsByEmail(adminRegisterRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        // 3. 관리자 계정 생성
        AdminUser adminUser = AdminUser.builder()
            .email(adminRegisterRequestDto.getEmail())
            .passwordHash(passwordEncoder.encode(adminRegisterRequestDto.getPassword()))
            .isActive(true)
            .role("ROLE_ADMIN")
            .build();

        adminUserRepository.save(adminUser);

    }


}
