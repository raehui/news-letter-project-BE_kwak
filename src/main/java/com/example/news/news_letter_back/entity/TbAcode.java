package com.example.news.news_letter_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_acode")
public class TbAcode {
    @Id
    @Column(name = "acode", nullable = false)
    private String acode; // SUBSCRIBE, POST, EMAIL

    @Column(name = "aname", nullable = false, unique = true)
    private String aname; // 구독상태, 홈페이지_글상태, 메일용_글상태

    @Column(nullable = false)
    private String deleted = "NO";
}
