package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.news.*;
import com.example.news.news_letter_back.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<SendNewsResponseDto> sendNews(@Valid @RequestBody SendNewsRequestDto request) {
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
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String sort
    ) {
        Sort.Direction direction;
        if (sort.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, "createdAt"));
        return service.getEmailList(pageable);
    }

    // 이메일 템플릿 등록
    @PostMapping("/create")
    public ResponseEntity<CreateEmailResponseDto> createEmail(@RequestBody CreateEmailRequestDto request) {
        return service.createEmail(request);
    }
}
