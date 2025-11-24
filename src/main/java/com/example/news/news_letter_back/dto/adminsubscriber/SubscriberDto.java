package com.example.news.news_letter_back.dto.adminsubscriber;

import com.example.news.news_letter_back.entity.Subscriber;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriberDto {
    private Long subscriberId;
    private String email;
    private String statusAcode;         // SUBSCRIBE
    private String statusBcode;         // SUB, UNSUB
    private String unsubscribeToken;    // 구독 해지 토큰
    private OffsetDateTime createdAt;   // 구독날짜
    private OffsetDateTime updatedAt;   // 변경날짜

    // entity -> dto 변환 메서드
    // 서버가 응답할 때 사용함
    public static SubscriberDto toDto(Subscriber subscriber) {
        // 매개변수의 Subscriber entity 객체를 SubscriberDto로 만들어서 리턴함
        return SubscriberDto.builder()
                .subscriberId(subscriber.getSubscriber_id())
                .email(subscriber.getEmail())
                .statusAcode(subscriber.getStatusAcode())
                .statusBcode(subscriber.getStatusBcode())
                .unsubscribeToken(subscriber.getUnsubscribeToken())
                .createdAt(subscriber.getCreatedAt())
                .updatedAt(subscriber.getUpdatedAt())
                .build();
    }
}
