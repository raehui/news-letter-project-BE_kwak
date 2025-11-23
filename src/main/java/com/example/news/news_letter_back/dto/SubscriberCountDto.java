package com.example.news.news_letter_back.dto;

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
public class SubscriberCountDto {
    // 구독자 수
    private Long totalSubscribers;
    // 비구독자 수
    private Long totalUnsubscribers;



}
