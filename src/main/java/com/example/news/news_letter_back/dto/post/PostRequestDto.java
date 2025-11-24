package com.example.news.news_letter_back.dto.post;

import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Post;
import lombok.Data;

@Data
public class PostRequestDto {
    // 글 ID
    // 작성자
    // 제목
    // 컨텐츠 내용
    // 저장상태(PUBLISHED, DRAFT, DELETED)
    private Long postId;
    private AdminUser adminUser;
    private String title;
    private String contentHtml;
    
    // dto를 Entity로 변경
    public Post toEntity() {
        return Post.builder()
            .postId(postId)
            .adminUser(this.adminUser)
            .title(this.title)
            .contentHtml(this.contentHtml)
            .build();
    }


}
