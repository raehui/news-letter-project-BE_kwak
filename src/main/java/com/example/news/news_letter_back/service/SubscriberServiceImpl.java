package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SubscriberDto;
import com.example.news.news_letter_back.dto.SubscriberListItemDto;
import com.example.news.news_letter_back.dto.SubscriberPageResponse;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
