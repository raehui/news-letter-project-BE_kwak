package com.example.news.news_letter_back.dto.post;

import com.example.news.news_letter_back.entity.Post;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostListInfoDto {
    // 글 목록 열 : 제목, 발행상태 발행일시
    private String title;
    private String statusBcode;
    private OffsetDateTime publishedAt;

    public PostListInfoDto(Post post) {
        this.title = post.getTitle();
        this.statusBcode = post.getStatusBcode();
        this.publishedAt = post.getPublishedAt();
    }

    public static PostListInfoDto fromEntity(Post post) {
        return PostListInfoDto.builder()
            .title(post.getTitle())
            .statusBcode(post.getStatusBcode())
            .publishedAt(post.getPublishedAt())
            .build();
    }
}
