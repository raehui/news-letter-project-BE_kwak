package com.example.news.news_letter_back.dto.news;

import lombok.Builder;

@Builder
public class CreateEmailResponseDto {
    Long id;
    String message;

    public static CreateEmailResponseDto fromEntity(String message) {
        return CreateEmailResponseDto.builder().message(message).build();
    }

    public static CreateEmailResponseDto fromEntity(Long id, String message) {
        return CreateEmailResponseDto.builder().id(id).message(message).build();
    }
}
