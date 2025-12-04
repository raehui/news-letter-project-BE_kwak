package com.example.news.news_letter_back.dto.news;

import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Newsletter;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmailRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    public static Newsletter toEntity(CreateEmailRequestDto dto, AdminUser admin) {
        return Newsletter.builder()
                .newsTitle(dto.title)
                .newsContentHtml(dto.contents)
                .statusAcode("EMAIL")
                .statusBcode("DRAFT")
                .scheduledAt(null)
                .publishedAt(null)
                .adminUser(admin)
                .build();
    }
}
