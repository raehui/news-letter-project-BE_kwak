package com.example.news.news_letter_back.dto.post;

import com.example.news.news_letter_back.entity.Post;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 글 상세보기 글 정보
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostInfoDto {
    private String title;
    private String contentHtml;
    private String statusBcode;
    private OffsetDateTime createdAt;
    private OffsetDateTime publishedAt;

    public static PostInfoDto fromEntity(Post post) {
        return PostInfoDto.builder()
            .title(post.getTitle())
            .contentHtml(post.getContentHtml())
            .statusBcode(post.getStatusBcode())
            .createdAt(post.getCreatedAt())
            .publishedAt(post.getPublishedAt())
            .build();
    }

}
