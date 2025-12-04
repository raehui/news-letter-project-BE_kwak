package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.SendNewsRequestDto;
import com.example.news.news_letter_back.dto.news.CreateEmailRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateRequestDto;
import com.example.news.news_letter_back.dto.news.EditEmailTemplateResponseDto;
import com.example.news.news_letter_back.dto.news.GetNewsletterListResponseDto;
import com.example.news.news_letter_back.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController// JSON 형태로 데이터로 받고 응답함
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    @Autowired
    private NewsService service;

    // 뉴스레터 이메일을 전송
    @PostMapping("/send")
    public ResponseEntity<?> sendNews(@Valid @RequestBody SendNewsRequestDto request) {
        return service.sendNews(request);
    }

    // 이메일 수정
    @PostMapping("/edit")
    public ResponseEntity<EditEmailTemplateResponseDto> editEmailTemplate(@RequestBody EditEmailTemplateRequestDto request) {
        return service.editEmailTemplate(request);
    }

    // 이메일 템플릿 목록
    @GetMapping("/list")
    public ResponseEntity<GetNewsletterListResponseDto> getEmailList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return service.getEmailList(pageable);
    }

    // 이메일 템플릿 등록
    @PostMapping("/create")
    public ResponseEntity<?> createEmail(@RequestBody CreateEmailRequestDto request) {
        return service.createEmail(request);
    }
}
