package com.example.news.news_letter_back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Override
    public ResponseEntity<?> sendNews() {
        return ResponseEntity.ok(
                Map.of("message", "")
        );
    }
}
