package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.entity.Post;
import com.example.news.news_letter_back.entity.Subscriber;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

    // 검색조건 없을 때, 기본 목록 가져오기
    public List<Post> findAll();

    // 검색조건 (제목, 발행상태)
    public List<Post> findByTitleContainingAndStatusBcode(String title, String statusBcode);

    public List<Post> findByTitleContaining(String title);

    public List<Post> findByStatusBcode(String statusBcode);
}
