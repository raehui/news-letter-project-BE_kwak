package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateRequestDto;
import org.springframework.http.ResponseEntity;

public interface NewsService {
    public ResponseEntity<?> sendNews(SendNewsRequestDto request);

    public ResponseEntity<?> editEmailTemplate(EditEmailTemplateRequestDto request);
}
