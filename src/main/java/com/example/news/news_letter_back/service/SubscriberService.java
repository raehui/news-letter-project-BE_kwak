package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Transactional
    public ResponseEntity<?> subscribe(String email) {
        // 1. 이메일 형식 검사 (간단하게 contains로만 체크)
        if (email == null || !email.contains("@")) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "INVALID_EMAIL_FORMAT", "message", "이메일 형식이 올바르지 않습니다.")
            );
        }

        // 2. 기존 구독 여부 확인
        Optional<Subscriber> existing = subscriberRepository.findByEmail(email);
        if (existing.isPresent()) {
            return ResponseEntity.status(409).body(
                Map.of("error", "DUPLICATE_EMAIL", "message", "이미 구독된 이메일입니다.")
            );
        }

        // 3. 새로 구독 저장
        Subscriber newSubscriber = Subscriber.builder()
                .email(email)
                .statusAcode("SUBSCRIBE")
                .statusBcode("SUB")
                .unsubscribeToken(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        subscriberRepository.save(newSubscriber);

        return ResponseEntity.ok(
            Map.of("message", "구독이 완료되었습니다.", "email", newSubscriber.getEmail())
        );
    }
}