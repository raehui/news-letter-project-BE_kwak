package com.example.news.news_letter_back.dto.news;

import lombok.Builder;

@Builder
public class SendNewsResponseDto {
    String message;

    public static SendNewsResponseDto fromEntity(String message) {
        return SendNewsResponseDto.builder().message(message).build();
    }
}
