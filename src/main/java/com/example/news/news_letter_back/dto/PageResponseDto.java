package com.example.news.news_letter_back.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PageResponseDto<T> {

    private List<T> items;
    private int page;          // 현재 페이지
    private int size;          // 페이지 크기
    private long totalElements;// 전체 개수
    private int totalPages;    // 전체 페이지 수
    private boolean hasNext;   // 다음 페이지 존재 여부
    private boolean hasPrevious;

    public static <T> PageResponseDto<T> from(Page<T> page) {
        return new PageResponseDto<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.hasNext(),
            page.hasPrevious()
        );
    }
}
