package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.TbBcode;
import com.example.news.news_letter_back.entity.TbBcodeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BcodeRepository extends JpaRepository<TbBcode, TbBcodeId> {
}