package com.example.news.news_letter_back.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendNewsRequestDto {
    private String title;
}
