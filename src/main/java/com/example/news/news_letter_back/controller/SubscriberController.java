package com.example.news.news_letter_back.controller;


import com.example.news.news_letter_back.dto.PageResponseDto;
import com.example.news.news_letter_back.dto.adminsubscriber.SubscriberAdminRequestDto;
import com.example.news.news_letter_back.dto.adminsubscriber.SubscriberCountDto;
import com.example.news.news_letter_back.dto.adminsubscriber.SubscriberListInfoDto;
import com.example.news.news_letter_back.service.SubscriberAdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.news_letter_back.dto.SubscriberRequestDto;
import com.example.news.news_letter_back.service.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController// JSON 형태로 데이터로 받고 응답함
// application.yml에 설정해서 작성안해도 자동으로 붙음
//@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "News", description = "구독 신청, 취소, 관리 등")
public class SubscriberController {

    @Autowired
    private SubscriberAdminService adminservice;
    @Autowired
    private SubscriberService service;
    // 구독자, 비구독자 통계 데이터 가져오기
    @GetMapping("/admin/subscriber/count")
    public SubscriberCountDto getSubscriberCount() {
        return adminservice.getCount();
    }


    // 검색조건 : 이메일 주소, 구독 상태에 맞는 구독자 목록 조회
    @GetMapping("/admin/subscriber/list")
    public PageResponseDto<SubscriberListInfoDto> getSubscriber(
        @RequestBody SubscriberAdminRequestDto subadminrequestdto) {
        Page<SubscriberListInfoDto> pageResult=adminservice.getSubscriber(subadminrequestdto);
        return PageResponseDto.from(pageResult);
    }

    // 구독자 정보 영구 삭제
    @DeleteMapping("/admin/subscriber/delete/{subscriberId}")
    public String delete(@PathVariable Long subscriberId) {
        return adminservice.delete(subscriberId);
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
