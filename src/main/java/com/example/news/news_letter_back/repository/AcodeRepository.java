package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.TbAcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcodeRepository extends JpaRepository<TbAcode, String> {
}