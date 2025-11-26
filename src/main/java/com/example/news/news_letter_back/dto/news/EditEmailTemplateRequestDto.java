package com.example.news.news_letter_back.dto.news;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EditEmailTemplateRequestDto {
    String templateName;
    String title;
    String contents;
}
