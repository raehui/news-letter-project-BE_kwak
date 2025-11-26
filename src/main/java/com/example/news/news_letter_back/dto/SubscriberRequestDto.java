package com.example.news.news_letter_back.dto;

import com.example.news.news_letter_back.entity.Subscriber;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class SubscriberRequestDto {
    private String email;

    public static Subscriber toEntity(String email) {
        return Subscriber.builder()
                .email(email)
                .statusAcode("SUBSCRIBE")
                .statusBcode("SUB")
                .unsubscribeToken(UUID.randomUUID().toString())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
    }
}
