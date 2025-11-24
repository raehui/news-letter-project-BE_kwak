package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.entity.Post;
import com.example.news.news_letter_back.repository.PostRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PostSevice {

    @Autowired
    private PostRepository postRepository;

    // 글 임시저장
    public String draft(@RequestBody PostRequestDto postRequestDto) {
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


}
