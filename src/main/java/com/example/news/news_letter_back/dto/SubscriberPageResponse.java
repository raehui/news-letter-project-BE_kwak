package com.example.news.news_letter_back.dto;

import java.util.List;
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
public class SubscriberPageResponse {
    private Summary summary;
    private List<SubscriberListItemDto> subscriberList;

    // ???
    @Builder
    @Getter
    public static class Summary {
        private Long totalSubscribers;
        private Long totalUnsubscribers;
        // 반송은 일단 빼고 생각하기
    }
}
