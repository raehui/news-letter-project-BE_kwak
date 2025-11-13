package com.example.news.news_letter_back.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_bcode")
public class TbBcode {
    @EmbeddedId
    private TbBcodeId id; // 복합키 acode + bcode
    // @EmbeddedId를 쓸 때는 키에 포함된 컬럼 @Column 사용 금지

    @Column(name = "bname",nullable = false)
    private String bname; // 구독, 비구독, 발행, 삭제, 임시저장

    @Column(nullable = false)
    private String deleted = "NO";

    // 연관관계
    // acode 부분을 FK로 맵핑
    @MapsId("acode") // 복합키(acode,bcode) 안의 acode 와 연결되어 있음
    @ManyToOne(fetch = FetchType.LAZY) // 여러 bcode 가 하나의 acode 가리킴
    @JoinColumn(name = "acode", referencedColumnName = "acode") // 외래키 컬럼 이름 지정
    private TbAcode acode;
}
