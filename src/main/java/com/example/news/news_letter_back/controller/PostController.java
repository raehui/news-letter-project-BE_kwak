package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.post.PostListInfoDto;
import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.dto.post.PostResponseDto;
import com.example.news.news_letter_back.service.PostSevice;
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

@RestController// JSON 형태로 데이터로 받고 응답함
@RequiredArgsConstructor
public class PostController {

    @Autowired private PostSevice service;
    // 글 발행
    @PostMapping("/admin/post/publish/{postId}")
    public String publish(@PathVariable Long postId) {
        return service.publish(postId);
    }
    // 글 임시저장
    @PostMapping("/admin/post/draft")
    public String draft(@RequestBody PostRequestDto postRequestDto) {
        return service.draft(postRequestDto);
    }
    // 글 수정
    @PatchMapping("/admin/post/update")
    public String update(@RequestBody PostRequestDto postRequestDto) {
        return service.update(postRequestDto);
    }
    // 글 삭제
    @DeleteMapping("/admin/post/delete/{postId}")
    public String delete(@PathVariable Long postId) {
        return service.delete(postId);
    }
    // 글 목록
    // 검색조건 : 제목, 글 상태에 맞는 글 목록 조회하기
    @GetMapping("/admin/post/list")
    public List<PostListInfoDto> getPost(@RequestBody PostRequestDto postRequestDto) {
        return service.getPost(postRequestDto);
    }

    // 글 상세
    // 제목, 내용, 상태(발행, 임시저장), 발행날짜 or 수정날짜
    @GetMapping("/admin/post/detail/{postId}")
    public PostResponseDto getPostDetail(@PathVariable Long postId) {
        return service.getPostDetail(postId);
    }


}
