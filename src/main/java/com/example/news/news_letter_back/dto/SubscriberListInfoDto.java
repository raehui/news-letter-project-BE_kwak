package com.example.news.news_letter_back.dto;

import com.example.news.news_letter_back.entity.Subscriber;
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
// 구독자 관리 화면에서 필요한 정보만 있는 Dto
public class SubscriberListInfoDto {
    // 이메일 주소, 구독일, 구독상태
    private String email;
    private OffsetDateTime created_at;
    private String statusBcode;

    public SubscriberListInfoDto(Subscriber subscriber) {
        this.email = subscriber.getEmail();
        this.created_at = subscriber.getCreatedAt();
        this.statusBcode = subscriber.getStatusBcode();

    }

    public static SubscriberListInfoDto fromEntity(Subscriber subscriber){
        return SubscriberListInfoDto.builder()
            .email(subscriber.getEmail())
            .created_at(subscriber.getCreatedAt())
            .statusBcode(subscriber.getStatusBcode())
            .build();
    }
}
