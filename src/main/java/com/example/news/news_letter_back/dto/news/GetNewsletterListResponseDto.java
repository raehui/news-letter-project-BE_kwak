package com.example.news.news_letter_back.dto.news;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetNewsletterListResponseDto {
    private List<NewsletterDto> list;

    public static GetNewsletterListResponseDto fromEntity(List<NewsletterDto> newsletters) {
        return GetNewsletterListResponseDto.builder()
                .list(newsletters)
                .build();
    }
}