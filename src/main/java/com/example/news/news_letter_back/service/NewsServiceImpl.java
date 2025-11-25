package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.news.news_letter_back.service.EmailService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<?> sendNews(SendNewsRequestDto request) {
        // 현재 구독자 목록 가져오기
        List<Subscriber> subscribers = subscriberRepository.findByStatusAcodeAndStatusBcode("SUBSCRIBE", "SUB");

        emailService.sendBulkTemplateEmail(subscribers, request.getTitle(), request.getContents());

        return ResponseEntity.ok(
                Map.of("message", "")
        );
    }
}
