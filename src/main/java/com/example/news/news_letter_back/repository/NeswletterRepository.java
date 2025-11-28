package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeswletterRepository extends JpaRepository<Newsletter,Long> {

    // 검색조건 없을 때, 기본 목록 가져오기
    public List<Newsletter> findAll();

    // 검색조건 (제목, 발행상태)
//    public List<Newsletter> findByTitleContainingAndStatusBcode(String newsTitle, String statusBcode);

//    public List<Newsletter> findByTitleContaining(String title);

//    public List<Newsletter> findByStatusBcode(String statusBcode);

}
