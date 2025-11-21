package com.example.news.news_letter_back.service;

import org.springframework.http.ResponseEntity;

public interface NewsService {

    public ResponseEntity<?> sendNews();

}
