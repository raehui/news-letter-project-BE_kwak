package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.news.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface NewsService {
    public ResponseEntity<SendNewsResponseDto> sendNews(SendNewsRequestDto request);

    public ResponseEntity<EditEmailTemplateResponseDto> editEmailTemplate(EditEmailTemplateRequestDto request);

    public ResponseEntity<GetNewsletterListResponseDto> getEmailList(Pageable pageable);

    public ResponseEntity<CreateEmailResponseDto> createEmail(CreateEmailRequestDto request);
}
