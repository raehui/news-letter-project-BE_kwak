package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SubscriberAdminRequestDto;
import com.example.news.news_letter_back.dto.SubscriberCountDto;
import com.example.news.news_letter_back.dto.SubscriberListInfoDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.example.news.news_letter_back.repository.SubscriberAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberAdminService {

    @Autowired
    private SubscriberAdminRepository subrepo;

    // 이메일, 구독상태에 맞는 구독자 리스트
    public List<SubscriberListInfoDto> getSubscriber(
        SubscriberAdminRequestDto subscriberAdminRequestDto) {
        // 검색조건에 맞는 리스트
        List<Subscriber> entities;

        // 검색 조건 가져오기
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
            .map(SubscriberListInfoDto::new).toList();
        return SubscriberList;
    }

    // 구독자, 비구독자 통계 정보
    public SubscriberCountDto getCount() {
        // 통계정보
        Long subcount = subrepo.countByStatusBcode("SUB");
        Long unsubcount = subrepo.countByStatusBcode("UNSUB");

        SubscriberCountDto countDto = SubscriberCountDto.builder()
            .totalSubscribers(subcount)
            .totalUnsubscribers(unsubcount).build();

        return countDto;
    }

}
