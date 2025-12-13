package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.AdminUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {
    // 회원가입 시 이메일 중복 검사
    boolean existsByEmail(String email);

    Optional<AdminUser> findByEmail(String email);
}
