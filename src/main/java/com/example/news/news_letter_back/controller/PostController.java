package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.PageResponseDto;
import com.example.news.news_letter_back.dto.post.PostListInfoDto;
import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.dto.post.PostResponseDto;
import com.example.news.news_letter_back.service.PostSevice;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private PostSevice service;

    // (새로 작성한) 글 발행
    @PostMapping("/admin/post/publish")
    public String publishNew(@RequestBody PostRequestDto postRequestDto) {
        return service.publishNew(postRequestDto);
    }

    // (새로 작성한)글 임시저장
    @PostMapping("/admin/post/draft")
    public String draft(@RequestBody PostRequestDto postRequestDto) {
        return service.draft(postRequestDto);
    }

    // (임시저장한) 글을 재임시저장
    @PatchMapping("/admin/post/update/redraft")
    public String updateRedraft(@RequestBody PostRequestDto postRequestDto) {
        return service.updateRedraft(postRequestDto);
    }

    // (임시저장한) 글을 발행하기
    @PatchMapping("/admin/post/update/publish")
    public String updatePublish(@RequestBody PostRequestDto postRequestDto) {
        return service.updatePublish(postRequestDto);
    }

    // 글 삭제
    @DeleteMapping("/admin/post/delete/{postId}")
    public String delete(@PathVariable Long postId) {
        return service.delete(postId);
    }

    // 글 목록
    // 검색조건 : 제목, 글 상태에 맞는 글 목록 조회하기
    @GetMapping("/admin/post/list")
    public PageResponseDto<PostListInfoDto> getPost(@RequestBody PostRequestDto postRequestDto) {
        Page<PostListInfoDto> pageResult = service.getPost(postRequestDto);
        return PageResponseDto.from(pageResult);
    }

    // 글 상세
    // 제목, 내용, 상태(발행, 임시저장), 발행날짜 or 수정날짜
    @GetMapping("/admin/post/detail/{postId}")
    public PostResponseDto getPostDetail(@PathVariable Long postId) {
        return service.getPostDetail(postId);
    }


}
