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

    // 페이징 처리 정보
    private int page = 0; // 현재 조회하고 있는 페이지의 차례
    private int size = 6; // 한 페이지에 나타나는 행의 개수


}
