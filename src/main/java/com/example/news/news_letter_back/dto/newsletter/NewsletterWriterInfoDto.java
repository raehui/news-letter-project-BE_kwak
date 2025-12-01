package com.example.news.news_letter_back.dto.newsletter;

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
public class NewsletterWriterInfoDto {
    private String email;

    public static NewsletterWriterInfoDto fromEntity(AdminUser  adminUser){
        return NewsletterWriterInfoDto.builder()
            .email(adminUser.getEmail())
            .build();
    }
    
}
