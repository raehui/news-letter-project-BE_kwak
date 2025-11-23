package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.SubscriberAdminRequestDto;
import com.example.news.news_letter_back.dto.SubscriberResponseDto;
import com.example.news.news_letter_back.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController// JSON 형태로 데이터로 받고 응답함
@RequiredArgsConstructor
public class SubscriberController {

    @Autowired
    private SubscriberService service;

    // 구독자 목록 조회
//    @GetMapping("/admin/users")
//    public List<SubscriberDto> findAllByOrderByCreatedAtDesc() {
//        return service.findAllByOrderByCreatedAtDesc();
//    }

    // 검색조건 : 이메일 주소, 구독 상태에 맞는 구독자 목록 조회
    @GetMapping("/admin/subscriber/list")
    public SubscriberResponseDto getSubscriber(@RequestBody SubscriberAdminRequestDto subadminrequestdto) {
        return service.getSubscriber(subadminrequestdto);
    }
}
