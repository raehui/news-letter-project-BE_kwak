package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SubscriberDto;
import com.example.news.news_letter_back.dto.SubscriberListItemDto;
import com.example.news.news_letter_back.dto.SubscriberPageResponse;
import com.example.news.news_letter_back.dto.SubscriberRequestDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import com.example.news.news_letter_back.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class SubscriberServiceImpl implements SubscriberService{

    @Autowired private SubscriberRepository subrepo;

    @Override
    public List<SubscriberDto> findAllByOrderByCreatedAtDesc() {
        // 구독자 엔티티 리스트
        List<Subscriber> entityList = subrepo.findAllByOrderByCreatedAtDesc();
        
        // 응답할 때는 SubscriberDto의 목록으로 리런해야 함
        // 구독자 dto 리스트
        // .stream() -> 하나씩 꺼내서 반복문 처리함
        // .map() -> 다른 형태로 변환
        // .toList() -> 리스트로 다시 모음
        List<SubscriberDto> list = entityList.stream().map(SubscriberDto::toDto).toList();
        
        return list;
    }

    @Override
    public SubscriberPageResponse getSubscriber(String email, String status_bcode) {
        //1. 구독자 통계 정보 생성
        long subcount=subrepo.countByStatusBcode("SUB");
        long unsubcount=subrepo.countByStatusBcode("UNSUB");

        SubscriberPageResponse.Summary summary=SubscriberPageResponse.Summary.builder()
            .totalSubscribers(subcount)
            .totalUnsubscribers(unsubcount).build();

        //2. 해당 구독자 리스트
        List<Subscriber> entities;
        // 이메일&상태값 모두 존재
        if (email != null && !email.isBlank() && status_bcode != null && !status_bcode.equals("ALL")) {
            entities = subrepo.findByEmailContainingAndStatusBcode(email, status_bcode);
        } else if (email != null && !email.isBlank()) {
            entities = subrepo.findByEmailContaining(email);
        } else if (status_bcode != null && !status_bcode.equals("ALL")) {
            entities = subrepo.findByStatusBcode(status_bcode);
        } else {
            entities = subrepo.findAllByOrderByCreatedAtDesc();
        }

        // 3. 엔티티 → DTO 매핑
        List<SubscriberListItemDto> SubscriberItemlist = entities.stream()
            .map(s -> SubscriberListItemDto.builder()
                .email(s.getEmail())
                .created_at(s.getCreatedAt())
                .statusBcode(s.getStatusBcode())
                .build())
            .toList();


        return SubscriberPageResponse.builder()
            .summary(summary)
            .subscriberList(SubscriberItemlist)
            .build();
    }

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
        subscriber.setUpdatedAt(OffsetDateTime.from(Instant.now()));
        subrepo.save(subscriber);

        return ResponseEntity.ok(
                Map.of("message", "구독이 취소되었습니다.")
        );
    }
}
