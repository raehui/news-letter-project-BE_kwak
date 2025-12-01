package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.newsletter.NeswletterRequestDto;
import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsletterController {

    @Autowired
    private NewsletterService service;

    // 글 발행
    @PostMapping("/admin/news/publish/{newsletterId}")
    public String publishNews(@PathVariable Long newsletterId) {
        return service.publishnews(newsletterId);
    }

    // 글 임시저장
    @PostMapping("/admin/news/draft")
    public String draftNews(@RequestBody NeswletterRequestDto newsletterRequestDto) {
        return service.draftnews(newsletterRequestDto);
    }

    // 글 수정
    @PatchMapping("/admin/news/update")
    public String update(@RequestBody NeswletterRequestDto newsletterRequestDto) {
        return service.updatenews(newsletterRequestDto);
    }



    // 글 목록
    // 글 상세


}
