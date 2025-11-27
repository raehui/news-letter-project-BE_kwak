package com.example.news.news_letter_back.dto.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditEmailTemplateResponseDto {
    String message;
    String error;

    public static EditEmailTemplateResponseDto fromEntity(Map<String, String> result) {
        return EditEmailTemplateResponseDto.builder()
                .message(result.get("message"))
                .error(result.get("error"))
                .build();
    }
}
