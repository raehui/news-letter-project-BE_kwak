package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateResponseDto;
import com.example.news.news_letter_back.dto.news.GetNewsletterListResponseDto;
import com.example.news.news_letter_back.dto.news.NewsletterDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.infra.SESService;
import com.example.news.news_letter_back.repository.NewsletterRepository;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private NewsletterRepository newsletterRepository;
    @Autowired
    private SESService sesService;

    @Override
    public ResponseEntity<?> sendNews(SendNewsRequestDto request) {
        // 현재 구독자 목록 가져오기
        List<Subscriber> subscribers = subscriberRepository.findByStatusAcodeAndStatusBcode("SUBSCRIBE",
                "SUB");

        sesService.sendBulkTemplateEmail(subscribers, request.getTitle(), request.getContents());

        return ResponseEntity.ok(
                Map.of("message", "")
        );
    }

    @Override
    public ResponseEntity<EditEmailTemplateResponseDto> editEmailTemplate(
            EditEmailTemplateRequestDto request) {
        try {
            if (sesService.editEmailTemplate(request)) {
                return ResponseEntity.ok(
                        EditEmailTemplateResponseDto.fromEntity(Map.of("message", "success"))
                );
            }

            return ResponseEntity.internalServerError()
                    .body(EditEmailTemplateResponseDto.fromEntity(Map.of("message", "fail")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(EditEmailTemplateResponseDto.fromEntity(Map.of("message", "fail", "error", "TEMPLATE_UPDATE_FAILED")));
        }
    }

    @Override
    public ResponseEntity<GetNewsletterListResponseDto> getEmailList(Pageable pageable) {
        try {
            var pages = newsletterRepository.findAll(pageable);

            List<NewsletterDto> newsletters = pages
                    .map(NewsletterDto::fromEntity)
                    .toList();

            GetNewsletterListResponseDto result = GetNewsletterListResponseDto.fromEntity(newsletters);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(GetNewsletterListResponseDto.fromEntity(List.of()));
        }
    }
}
