package com.example.news.news_letter_back.dto.adminsubscriber;

import lombok.Data;

@Data
// 구독자 리스트 검색조건 = 이메일, 구독여부
public class SubscriberAdminRequestDto {
    // 검색조건
    private String email;
    private String statusBcode; // SUB, UNSUB
    // 페이징 처리 정보
    private int page = 0; // 현재 조회하고 있는 페이지의 차례
    private int size = 6; // 한 페이지에 나타나는 행의 개수
}


