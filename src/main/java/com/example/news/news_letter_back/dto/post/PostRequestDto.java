package com.example.news.news_letter_back.dto.post;

import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Post;
import lombok.Data;

@Data
public class PostRequestDto {
    // 글ID
    // 작성자ID
    // 제목
    // 컨텐츠 내용
    // 저장상태(PUBLISHED, DRAFT, DELETED)
    private Long postId;
    private Long adminId;
    private String title;
    private String contentHtml;
    private String statusBcode;




}
