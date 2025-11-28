package com.example.news.news_letter_back.dto.post;

import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Post;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponseDto {

    private PostInfoDto postInfoDto;
    private WriterInfoDto writerInfoDto;

    public static PostResponseDto fromEntity(Post post, AdminUser adminUser) {
        return PostResponseDto.builder()
            .postInfoDto(PostInfoDto.fromEntity(post))
            .writerInfoDto(WriterInfoDto.fromEntity(adminUser))
            .build();

    }






}
