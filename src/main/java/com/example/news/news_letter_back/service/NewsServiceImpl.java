package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateRequestDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.infra.SESService;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private SESService sesService;

    @Override
    public ResponseEntity<?> sendNews(SendNewsRequestDto request) {
        // 현재 구독자 목록 가져오기
        List<Subscriber> subscribers = subscriberRepository.findByStatusAcodeAndStatusBcode("SUBSCRIBE", "SUB");

        sesService.sendBulkTemplateEmail(subscribers, request.getTitle(), request.getContents());

        return ResponseEntity.ok(
                Map.of("message", "")
        );
    }

    @Override
    public ResponseEntity<?> editEmailTemplate(EditEmailTemplateRequestDto request) {
        try {
            if (sesService.editEmailTemplate(request)) {
                return ResponseEntity.ok(
                        Map.of("message", "success")
                );
            }

            return ResponseEntity.internalServerError().body(Map.of("message", "fail"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "fail", "error", "TEMPLATE_UPDATE_FAILED"));
        }
    }
}
