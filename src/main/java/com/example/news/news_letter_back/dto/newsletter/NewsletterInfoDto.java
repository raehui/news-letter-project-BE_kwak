package com.example.news.news_letter_back.dto.newsletter;

import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 글 상세보기 글 정보
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsletterInfoDto {
    private String newsTitle;
    private String contentHtml;
    private String statusBcode;
    private OffsetDateTime createdAt;
    private OffsetDateTime publishedAt;

    public static NewsletterInfoDto fromEntity(Newsletter newsletter) {
        return NewsletterInfoDto.builder()
            .newsTitle(newsletter.getNewsTitle())
            .contentHtml(newsletter.getContentHtml())
            .statusBcode(newsletter.getStatusBcode())
            .createdAt(newsletter.getCreatedAt())
            .publishedAt(newsletter.getPublishedAt())
            .build();
    }

}
