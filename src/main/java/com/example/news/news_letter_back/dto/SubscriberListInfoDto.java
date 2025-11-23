package com.example.news.news_letter_back.dto;

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
public class SubscriberListItemDto {
    // 이메일 주소, 구독일, 구독상태
    private String email;
    private OffsetDateTime created_at;
    private String statusBcode;

}
