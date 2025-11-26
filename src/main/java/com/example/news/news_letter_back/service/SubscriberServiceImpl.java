package com.example.news.news_letter_back.service;


import com.example.news.news_letter_back.dto.SubscriberRequestDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.repository.SubscriberAdminRepository;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import com.example.news.news_letter_back.utils.EmailValidator;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subrepo;


    @Override
    public ResponseEntity<?> subscribe(String email) {
        // 1. 이메일 형식 검사 (간단하게 contains로만 체크)
        if (!EmailValidator.isValid(email)) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "INVALID_EMAIL_FORMAT", "message", "이메일 형식이 올바르지 않습니다.")
            );
        }

        // 2. 기존 구독 여부 확인
        Optional<Subscriber> existing = subrepo.findByEmail(email);
        if (existing.isPresent()) {
            return ResponseEntity.status(409).body(
                Map.of("error", "DUPLICATE_EMAIL", "message", "이미 구독된 이메일입니다.")
            );
        }

        // 3. 새로 구독 저장
        Subscriber newSubscriber = SubscriberRequestDto.toEntity(email);

        subrepo.save(newSubscriber);

        return ResponseEntity.ok(
            Map.of("message", "구독이 완료되었습니다.", "email", newSubscriber.getEmail())
        );
    }

    @Override
    public ResponseEntity<?> unsubscribe(String token) {
        // 1. 토큰이 비었는지 확인
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "INVALID_TOKEN_FORMAT", "message", "구독 해지에 필요한 토큰이 존재하지 않습니다.")
            );
        }

        // 2. 기존 구독 여부 확인
        Optional<Subscriber> existing = subrepo.findByUnsubscribeToken(token);
        if (existing.isEmpty()) {
            return ResponseEntity.status(409).body(
                Map.of("error", "NOT_SUBSCRIBED", "message", "현재 구독중인 이메일이 아닙니다.")
            );
        }

        // 3. 구독 정보 수정
        Subscriber subscriber = existing.get();
        subscriber.setStatusBcode("UNSUB");
        subscriber.setUpdatedAt(OffsetDateTime.now());
        subrepo.save(subscriber);

        return ResponseEntity.ok(
            Map.of("message", "구독이 취소되었습니다.")
        );
    }

}
