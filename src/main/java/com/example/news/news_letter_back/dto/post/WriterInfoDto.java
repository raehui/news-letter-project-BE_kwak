package com.example.news.news_letter_back.dto.post;

import com.example.news.news_letter_back.entity.AdminUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 글 상세보기 작성자 정보
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WriterInfoDto {
    private String email;

    public static WriterInfoDto fromEntity(AdminUser  adminUser){
        return WriterInfoDto.builder()
            .email(adminUser.getEmail())
            .build();
    }
    
}
