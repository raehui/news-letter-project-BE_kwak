package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SubscriberAdminRequestDto;
import com.example.news.news_letter_back.dto.SubscriberCountDto;
import com.example.news.news_letter_back.dto.SubscriberDto;
import com.example.news.news_letter_back.dto.SubscriberListInfoDto;
import com.example.news.news_letter_back.dto.SubscriberResponseDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subrepo;

    @Override
    public SubscriberResponseDto getSubscriber(
        SubscriberAdminRequestDto subscriberAdminRequestDto) {
        // 통계정보
        Long subcount = subrepo.countByStatusBcode("SUB");
        Long unsubcount = subrepo.countByStatusBcode("UNSUB");

        SubscriberCountDto countDto = SubscriberCountDto.builder()
            .totalSubscribers(subcount)
            .totalUnsubscribers(unsubcount).build();

        // 검색조건에 맞는 리스트
        List<Subscriber> entities;
        String email = subscriberAdminRequestDto.getEmail();
        String status_bcode = subscriberAdminRequestDto.getStatusBcode();
        // 이메일&상태값 모두 존재
        if (email != null && !email.isBlank() && status_bcode != null && !status_bcode.equals(
            "ALL")) {
            entities = subrepo.findByEmailContainingAndStatusBcode(email, status_bcode);
        } else if (email != null && !email.isBlank()) {
            entities = subrepo.findByEmailContaining(email);
        } else if (status_bcode != null && !status_bcode.equals("ALL")) {
            entities = subrepo.findByStatusBcode(status_bcode);
        } else {
            entities = subrepo.findAllByOrderByCreatedAtDesc();
        }

        //엔티티를 dto에 맵핑
        List<SubscriberListInfoDto> SubscriberList = entities.stream()
            .map(s -> SubscriberListInfoDto.builder()
                .email(s.getEmail())
                .created_at(s.getCreatedAt())
                .statusBcode(s.getStatusBcode())
                .build()).toList();

        return SubscriberResponseDto.builder()
            .subscriberCount(countDto)
            .subscriberList(SubscriberList).build();

    }
}
