package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.Subscriber;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubscriberAdminRepository extends JpaRepository<Subscriber, Long> {

    // 구독자 목록 가져오기
    // 구독일 내림차순
    // 이메일, 구독일(creatde_at), 구독상태(b_name) -> 기본값
    public Page<Subscriber> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 구독하는 사람들의 수
    // 비구독하는 사람들의 수
    public Long countByStatusBcode(String statusBcode);

    // 검색조건 (이메일 + 상태)
    public Page<Subscriber> findByEmailContainingAndStatusBcode(String email, String statusBcode,
        Pageable pageable);

    public Page<Subscriber> findByEmailContaining(String email, Pageable pageable);

    public Page<Subscriber> findByStatusBcode(String statusBcode, Pageable pageable);


}
