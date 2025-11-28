package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.post.PostListInfoDto;
import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.dto.post.PostResponseDto;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Post;
import com.example.news.news_letter_back.repository.AdminUserRepository;
import com.example.news.news_letter_back.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PostSevice {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    AdminUserRepository adminUserRepository;

    // 글 임시저장
    public String draft(PostRequestDto postRequestDto) {
        Post post = postRequestDto.toEntity();
        post.istitle();
        postRepository.save(post);
        return "글을 임시저장 했습니다.";
    }

    // 글 발행하기
    public String publish(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException());
        post.publish();
        postRepository.save(post);

        return "글을 발행했습니다.";
    }

    // 글 삭제하기
    public String delete(Long postId) {
        postRepository.deleteById(postId);
        return "글을 삭제했습니다.";
    }

    // 글 수정하기
    public String update(PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postRequestDto.getPostId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글"));
        post.istitle();
        post.update(postRequestDto.getTitle(), postRequestDto.getContentHtml());
        postRepository.save(post);

        return "글을 수정했습니다.";
    }

    // 글 목록 가져오기
    public List<PostListInfoDto> getPost(PostRequestDto postRequestDto) {
        // 검색조건에 맞는 리스트
        List<Post> entities;

        // 검색조건 가져오기
        String title = postRequestDto.getTitle();
        String status_bcode = postRequestDto.getStatusBcode();

        // 검색조건에 따라서 글 가지고 오기
        if (title != null && !title.isBlank() && status_bcode != null && !status_bcode.equals(
            "ALL")) {
            entities = postRepository.findByTitleContainingAndStatusBcode(title, status_bcode);
        } else if (title != null && !title.isBlank()) {
            entities = postRepository.findByTitleContaining(title);
        } else if (status_bcode != null && !status_bcode.equals("ALL")) {
            entities = postRepository.findByStatusBcode(status_bcode);
        } else {
            entities = postRepository.findAll();
        }

        // 엔티티를 dto에 맵핑하기
        List<PostListInfoDto> PostList = entities.stream()
            .map(PostListInfoDto::new).toList();

        return PostList;
    }

    // 글 상세보기
    public PostResponseDto getPostDetail(Long postId) {
        // 글id로 원하는 글 데이터 찾기
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException());
        // 작성자 id로 작성자 데이터 찾기
        AdminUser adminuser =adminUserRepository.findById(postId)
            .orElseThrow();

        return PostResponseDto.fromEntity(post, adminuser);

    }


}
