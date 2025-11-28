package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.newsletter.NeswletterRequestDto;
import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
import com.example.news.news_letter_back.repository.AdminUserRepository;
import com.example.news.news_letter_back.repository.NeswletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {

    @Autowired
    private NeswletterRepository neswletterRepository;
    @Autowired
    AdminUserRepository adminUserRepository;

    // 글 임시저장
    public String draftnews(NeswletterRequestDto newsletterRequestDto) {
        AdminUser adminUser = adminUserRepository.findById(newsletterRequestDto.getAdminId())
            .orElseThrow(() -> new IllegalArgumentException());

        Newsletter newsletter = Newsletter.builder()
            .adminUser(adminUser)
            .newsTitle(newsletterRequestDto.getNewsTitle())
            .contentHtml(newsletterRequestDto.getContentHtml())
            .statusBcode(newsletterRequestDto.getStatusBcode())
            .build();

        newsletter.istitle();
        neswletterRepository.save(newsletter);
        return "뉴스레터를 임시저장 했습니다.";
    }


}
