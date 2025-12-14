package com.example.news.news_letter_back.dto.news;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendNewsRequestDto {
    @NotBlank(message = "이메일 제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "이메일 본문을 입력해 주세요.")
    private String contents;
}
