package com.example.news.news_letter_back.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@AllArgsConstructor
// 로그인 성공 응답
public class AdminLoginResponseDto {
    private Long adminId;
    private String email;
    private String role;
    private String accessToken;
}
