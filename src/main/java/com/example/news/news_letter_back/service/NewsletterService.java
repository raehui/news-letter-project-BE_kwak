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

    // 글 발행하기
    public String publishnews(Long newsletterId) {
        Newsletter newsletter = neswletterRepository.findById(newsletterId)
            .orElseThrow(() -> new IllegalArgumentException());
        newsletter.publish();
        neswletterRepository.save(newsletter);

        return "글을 발행했습니다.";
    }

    // 글 수정하기
    public String updatenews(NeswletterRequestDto newsletterRequestDto) {
        Newsletter newsletter = neswletterRepository.findById(newsletterRequestDto.getNewsletterId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글"));
        newsletter.istitle();
        newsletter.update(newsletterRequestDto.getNewsTitle(), newsletterRequestDto.getContentHtml(), newsletterRequestDto.getStatusBcode());
        neswletterRepository.save(newsletter);

        return "글을 수정했습니다.";
    }


}
