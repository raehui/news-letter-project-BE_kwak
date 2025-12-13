package com.example.news.news_letter_back.dto.admin;

import lombok.Data;
import org.hibernate.cache.spi.support.StorageAccess;

@Data
// 관리자 회원가입 시 보내는 정보
public class AdminRegisterRequestDto {
    private String email;
    private String password;
    private String inviteCode; // 관리자 가입 코드
}
