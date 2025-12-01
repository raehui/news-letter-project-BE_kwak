package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.dto.newsletter.NeswletterRequestDto;
import com.example.news.news_letter_back.dto.newsletter.NewsletterListInfoDto;
import com.example.news.news_letter_back.dto.newsletter.NewsletterResponseDto;
import com.example.news.news_letter_back.dto.post.PostListInfoDto;
import com.example.news.news_letter_back.dto.post.PostRequestDto;
import com.example.news.news_letter_back.dto.post.PostResponseDto;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
import com.example.news.news_letter_back.repository.AdminUserRepository;
import com.example.news.news_letter_back.repository.NeswletterRepository;
import java.util.List;
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
        Newsletter newsletter = neswletterRepository.findById(
                newsletterRequestDto.getNewsletterId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글"));
        newsletter.istitle();
        newsletter.update(newsletterRequestDto.getNewsTitle(),
            newsletterRequestDto.getContentHtml(), newsletterRequestDto.getStatusBcode());
        neswletterRepository.save(newsletter);

        return "글을 수정했습니다.";
    }

    // 글 삭제하기
    public String deletenews(Long newsletterId) {
        neswletterRepository.deleteById(newsletterId);
        return "글을 삭제했습니다.";
    }

    // 글 목록 가져오기
    public List<NewsletterListInfoDto> getnews(NeswletterRequestDto newsletterRequestDto) {
        // 검색조건에 맞는 리스트
        List<Newsletter> entities;

        // 검색조건 가져오기
        String NewsTitle = newsletterRequestDto.getNewsTitle();
        String status_bcode = newsletterRequestDto.getStatusBcode();

        // 검색조건에 따라서 글 가지고 오기
        if (NewsTitle != null && !NewsTitle.isBlank() && status_bcode != null && !status_bcode.equals(
            "ALL")) {
            entities = neswletterRepository.findBynewsTitleContainingAndStatusBcode(NewsTitle, status_bcode);
        } else if (NewsTitle != null && !NewsTitle.isBlank()) {
            entities = neswletterRepository.findBynewsTitleContaining(NewsTitle);
        } else if (status_bcode != null && !status_bcode.equals("ALL")) {
            entities = neswletterRepository.findByStatusBcode(status_bcode);
        } else {
            entities = neswletterRepository.findAll();
        }

        // 엔티티를 dto에 맵핑하기
        List<NewsletterListInfoDto> NewsletterList = entities.stream()
            .map(NewsletterListInfoDto::new).toList();

        return NewsletterList;
    }
    // 글 상세보기
    public NewsletterResponseDto getNewsletterDetail(Long newsletterId) {
        // 글id로 원하는 글 데이터 찾기
        Newsletter newsletter = neswletterRepository.findById(newsletterId)
            .orElseThrow(() -> new IllegalArgumentException());
        // 작성자 id로 작성자 데이터 찾기
        AdminUser adminuser = adminUserRepository.findById(newsletterId)
            .orElseThrow();

        return NewsletterResponseDto.fromEntity(newsletter, adminuser);

    }

}
