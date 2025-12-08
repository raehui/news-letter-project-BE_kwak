package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeswletterRepository extends JpaRepository<Newsletter, Long> {

    // 검색조건 없을 때, 기본 목록 가져오기
    public Page<Newsletter> findAll(Pageable pageable);

    // 검색조건 (제목, 발행상태)
    public Page<Newsletter> findBynewsTitleContainingAndStatusBcode(String newsTitle,
        String statusBcode, Pageable pageable);

    public Page<Newsletter> findBynewsTitleContaining(String newsTitle, Pageable pageable);

    public Page<Newsletter> findByStatusBcode(String statusBcode, Pageable pageable);

}
