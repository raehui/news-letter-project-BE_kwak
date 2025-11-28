package com.example.news.news_letter_back.dto.newsletter;

import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
import lombok.Data;

@Data
public class NeswletterRequestDto {
    // 작성자
    // 제목
    // 컨텐츠 내용
    // 저장상태(PUBLISHED, DRAFT, DELETED)
    private Long newsletterId;
    private Long adminId;
    private String newsTitle;
    private String contentHtml;
    private String statusBcode;



}
