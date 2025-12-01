package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.newsletter.NeswletterRequestDto;
import com.example.news.news_letter_back.dto.newsletter.NewsletterListInfoDto;
import com.example.news.news_letter_back.dto.newsletter.NewsletterResponseDto;
import com.example.news.news_letter_back.dto.post.PostListInfoDto;
import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.dto.post.PostResponseDto;
import com.example.news.news_letter_back.service.NewsletterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    // 글 삭제
    @DeleteMapping("/admin/news/delete/{newsletterId}")
    public String delete(@PathVariable Long newsletterId) {
        return service.deletenews(newsletterId);
    }

    // 글 목록
    // 검색조건 : 제목, 뉴스레터 발행 상태
    @GetMapping("/admin/news/list")
    public List<NewsletterListInfoDto> getNews(@RequestBody NeswletterRequestDto newsletterRequestDto) {
        return service.getnews(newsletterRequestDto);
    }
    // 글 상세
    // 제목, 내용, 상태(발행, 임시저장), 발행날짜 or 수정날짜
    @GetMapping("/admin/news/detail/{newsletterId}")
    public NewsletterResponseDto getPostDetail(@PathVariable Long newsletterId) {
        return service.getNewsletterDetail(newsletterId);
    }

}
