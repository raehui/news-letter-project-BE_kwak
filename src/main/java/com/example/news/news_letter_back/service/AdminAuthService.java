package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.admin.AdminLoginRequestDto;
import com.example.news.news_letter_back.dto.admin.AdminLoginResponseDto;
import com.example.news.news_letter_back.dto.admin.AdminRegisterRequestDto;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.repository.AdminUserRepository;
import com.example.news.news_letter_back.security.JwtTokenProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor // ???
public class AdminAuthService {

    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${admin.invite-code}")
    private String adminInviteCode;

    // 회원가입
    public void register(AdminRegisterRequestDto adminRegisterRequestDto) {

        String reqCode = adminRegisterRequestDto.getInviteCode() == null ? ""
            : adminRegisterRequestDto.getInviteCode().trim();
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

    // 로그인
    public AdminLoginResponseDto logoin(AdminLoginRequestDto adminLoginRequestDto) {
        AdminUser user = adminUserRepository.findByEmail(adminLoginRequestDto.getEmail())
            .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일 입니다."));
        if (!passwordEncoder.matches(adminLoginRequestDto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("비밀번호가 틀립니다.");
        }

        if (!user.getIsActive()) {
            throw new RuntimeException("비활성화 계정입니다.");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole());

        return AdminLoginResponseDto.builder()
            .adminId(user.getAdminId())
            .email(user.getEmail())
            .role(user.getRole())
            .accessToken(token)
            .build();
    }

}
