package com.example.news.news_letter_back.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginRequestDto {
    // 관리자 로그인 요청
    private String email;
    private String password;
}
