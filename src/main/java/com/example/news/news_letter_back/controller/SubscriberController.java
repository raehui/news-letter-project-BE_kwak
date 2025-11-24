package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.adminsubscriber.SubscriberAdminRequestDto;
import com.example.news.news_letter_back.dto.adminsubscriber.SubscriberCountDto;
import com.example.news.news_letter_back.dto.adminsubscriber.SubscriberListInfoDto;
import com.example.news.news_letter_back.service.SubscriberAdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController// JSON 형태로 데이터로 받고 응답함
@RequiredArgsConstructor
public class SubscriberController {

    @Autowired
    private SubscriberAdminService service;

    // 구독자 목록 조회
//    @GetMapping("/admin/users")
//    public List<SubscriberDto> findAllByOrderByCreatedAtDesc() {
//        return service.findAllByOrderByCreatedAtDesc();
//    }

    // 구독자, 비구독자 통계 데이터 가져오기
    @GetMapping("/admin/subscriber/count")
    public SubscriberCountDto getSubscriberCount() {
        return service.getCount();
    }

    // 검색조건 : 이메일 주소, 구독 상태에 맞는 구독자 목록 조회
    @GetMapping("/admin/subscriber/list")
    public List<SubscriberListInfoDto> getSubscriber(@RequestBody SubscriberAdminRequestDto subadminrequestdto) {
        return service.getSubscriber(subadminrequestdto);
    }

}
