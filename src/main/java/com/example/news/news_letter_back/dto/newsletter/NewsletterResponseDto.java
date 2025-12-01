package com.example.news.news_letter_back.dto.newsletter;

import com.example.news.news_letter_back.dto.post.PostInfoDto;
import com.example.news.news_letter_back.dto.post.WriterInfoDto;
import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.entity.Newsletter;
import com.example.news.news_letter_back.entity.Post;
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
public class NewsletterResponseDto {

    private NewsletterInfoDto newsletterInfoDto;
    private NewsletterWriterInfoDto newsletterWriterInfoDto;

    public static NewsletterResponseDto fromEntity(Newsletter newsletter, AdminUser adminUser) {
        return NewsletterResponseDto.builder()
            .newsletterInfoDto(NewsletterInfoDto.fromEntity(newsletter))
            .newsletterWriterInfoDto(NewsletterWriterInfoDto.fromEntity(adminUser))
            .build();

    }






}
