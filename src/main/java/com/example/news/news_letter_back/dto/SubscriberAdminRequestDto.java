package com.example.news.news_letter_back.dto;

import lombok.Data;

@Data
// 구독자 리스트 검색조건
public class SubscriberAdminRequestDto {
    private String email;
    private String statusBcode; // SUB, UNSUB
}
