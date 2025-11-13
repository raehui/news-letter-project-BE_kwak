package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {

}
