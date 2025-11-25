package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {
    // 구독자 목록 가져오기
    // 구독일 내림차순
    // 이메일, 구독일(creatde_at), 구독상태(b_name) -> 기본값
    public List<Subscriber> findAllByOrderByCreatedAtDesc();

    // 구독중인 사용자 가져오기
    public List<Subscriber> findByStatusAcodeAndStatusBcode(String statusACode, String statusBcode);
    
    // 구독하는 사람들의 수
    // 비구독하는 사람들의 수
    public Long countByStatusBcode(String statusBcode);
    
    // 검색조건 (이메일 + 상태)
    public List<Subscriber> findByEmailContainingAndStatusBcode(String email, String statusBcode);
    public List<Subscriber> findByEmailContaining(String email);
    public List<Subscriber> findByStatusBcode(String statusBcode);

    public Optional<Subscriber> findByEmail(String email);

    public Optional<Subscriber> findByUnsubscribeToken(String unsubscribeToken);

}
