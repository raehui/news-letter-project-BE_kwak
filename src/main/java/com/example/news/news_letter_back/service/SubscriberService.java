package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.SubscriberAdminRequestDto;
import com.example.news.news_letter_back.dto.SubscriberDto;
import com.example.news.news_letter_back.dto.SubscriberResponseDto;

import java.util.List;

public interface SubscriberService {
    // 구독자 목록 (생성날짜 내림차순)
//    public List<SubscriberDto> findAllByOrderByCreatedAtDesc();

    // 구독자 통계 + 구독자 리스트
    public SubscriberResponseDto getSubscriber(SubscriberAdminRequestDto subscriberAdminRequestDto);




}
