package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {

}
