package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SubscriberDto;
import com.example.news.news_letter_back.dto.SubscriberPageResponse;
import com.example.news.news_letter_back.entity.Subscriber;

import java.util.List;

public interface SubscriberService {
    // 구독자 목록 (생성날짜 내림차순)
    public List<SubscriberDto> findAllByOrderByCreatedAtDesc();

    // 구독자 통계 + 구독자 리스트
    public SubscriberPageResponse getSubscriber(String email, String status_bcode);



}
