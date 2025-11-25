package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import org.springframework.http.ResponseEntity;

public interface NewsService {
    public ResponseEntity<?> sendNews(SendNewsRequestDto request);
}
