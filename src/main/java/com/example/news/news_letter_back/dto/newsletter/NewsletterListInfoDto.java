package com.example.news.news_letter_back.dto.newsletter;

import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsletterListInfoDto {
    // 글 목록 열 : 제목, 발행상태 발행일시
    private String newsTitle;
    private String statusBcode;
    private OffsetDateTime publishedAt;

    public NewsletterListInfoDto(Newsletter newsletter) {
        this.newsTitle = newsletter.getNewsTitle();
        this.statusBcode = newsletter.getStatusBcode();
        this.publishedAt = newsletter.getPublishedAt();
    }

    public static NewsletterListInfoDto fromEntity(Newsletter newsletter) {
        return NewsletterListInfoDto.builder()
            .newsTitle(newsletter.getNewsTitle())
            .statusBcode(newsletter.getStatusBcode())
            .publishedAt(newsletter.getPublishedAt())
            .build();
    }
}
