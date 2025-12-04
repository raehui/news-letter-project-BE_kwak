package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import com.example.news.news_letter_back.dto.news.*;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.infra.SESService;
import com.example.news.news_letter_back.repository.AdminUserRepository;
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
    private AdminUserRepository adminUserRepository;
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

    @Override
    public ResponseEntity<?> createEmail(CreateEmailRequestDto request) {
        try {
            // 관리자 가져오기
            List<AdminUser> admins = adminUserRepository.findAll();
            if (admins.isEmpty()) {
                throw new RuntimeException("NO_ADMIN_USER_FOUND");
            }

            AdminUser admin = admins.get(0);
            Newsletter entity = CreateEmailRequestDto.toEntity(request, admin);


            var saved = newsletterRepository.save(entity);

            return ResponseEntity.ok(
                    Map.of(
                            "id", saved.getId(),
                            "message", "NEWSLETTER_CREATED"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.internalServerError()
                    .body(
                            Map.of(
                                    "message", "CREATE_FAILED"
                            )
                    );
        }
    }
}
