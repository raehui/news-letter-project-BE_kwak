package com.example.news.news_letter_back.dto.news;

import com.example.news.news_letter_back.entity.Newsletter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsletterDto {
    private Long id;               // newsletter PK
    private String title;          // newsTitle

    private String status;    // tb_bcode

    private String createdAt;
    private String scheduledAt;
    private String publishedAt;

    public static NewsletterDto fromEntity(Newsletter entity) {
        return NewsletterDto.builder()
                .id(entity.getId())
                .title(entity.getNewsTitle())
                .status(entity.getStatusBcode())
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null)
                .scheduledAt(entity.getScheduledAt() != null ? entity.getScheduledAt().toString() : null)
                .publishedAt(entity.getPublishedAt() != null ? entity.getPublishedAt().toString() : null)
                .build();
    }
}
