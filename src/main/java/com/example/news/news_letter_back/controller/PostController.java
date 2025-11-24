package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.service.PostSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController// JSON 형태로 데이터로 받고 응답함
@RequiredArgsConstructor
public class PostController {

    @Autowired private PostSevice service;
    // 글 발행
    @PostMapping("/admin/post/publish")
    public String publish(Long postId) {
        return service.publish(postId);
    }
    // 글 임시저장
    @PostMapping("/admin/post/draft")
    public String draft(@RequestBody PostRequestDto postRequestDto) {
        return service.draft(postRequestDto);
    }
    // 글 수정
    
    // 글 삭제
    
    // 글 목록
    
    // 글 상세



}
