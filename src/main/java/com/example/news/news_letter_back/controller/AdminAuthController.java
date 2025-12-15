package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.admin.AdminLoginRequestDto;
import com.example.news.news_letter_back.dto.admin.AdminLoginResponseDto;
import com.example.news.news_letter_back.dto.admin.AdminRegisterRequestDto;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
// 관리자 회원가입, 로그인
public class AdminAuthController {

    @Autowired
    private AdminAuthService adminAuthService;

    // 회원가입
    @PostMapping("/admin/auth/register")
    public String adminRegister(@RequestBody AdminRegisterRequestDto adminRegisterRequestDto) {
        adminAuthService.register(adminRegisterRequestDto);
        return "관리자 회원가입에 성공했습니다.";
    }

    // 로그인
    @PostMapping("/admin/auth/login")
    public AdminLoginResponseDto login(@RequestBody AdminLoginRequestDto adminLoginRequestDto) {
        return adminAuthService.logoin(adminLoginRequestDto);
    }
}
