package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import com.example.news.news_letter_back.dto.news.CreateEmailRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateResponseDto;
import com.example.news.news_letter_back.dto.news.GetNewsletterListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface NewsService {
    public ResponseEntity<?> sendNews(SendNewsRequestDto request);

    public ResponseEntity<EditEmailTemplateResponseDto> editEmailTemplate(EditEmailTemplateRequestDto request);

    public ResponseEntity<GetNewsletterListResponseDto> getEmailList(Pageable pageable);

    public ResponseEntity<?> createEmail(CreateEmailRequestDto request);
}
