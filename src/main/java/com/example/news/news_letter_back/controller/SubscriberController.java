package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.SubscriberPageResponse;
import com.example.news.news_letter_back.dto.SubscriberRequestDto;
import com.example.news.news_letter_back.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController// JSON 형태로 데이터로 받고 응답함
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubscriberController {

    @Autowired private SubscriberService service;

    // 구독자 목록 조회
//    @GetMapping("/admin/users")
//    public List<SubscriberDto> findAllByOrderByCreatedAtDesc() {
//        return service.findAllByOrderByCreatedAtDesc();
//    }

    // 검색조건 : 이메일 주소, 구독 상태 에 맞는 구독자 목록 조회
    @GetMapping("/admin/users")
    public SubscriberPageResponse getSubscriber(
        @RequestParam(required = false) String email,
        @RequestParam(defaultValue = "ALL") String status_bcode
    ) {
        return service.getSubscriber(email, status_bcode);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriberRequestDto request) {
        return service.subscribe(request.getEmail());
    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestParam("token") String token) {
        return service.unsubscribe(token);
    }
}
