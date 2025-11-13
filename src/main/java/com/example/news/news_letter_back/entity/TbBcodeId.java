package com.example.news.news_letter_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable // 다른 엔티티 안에 포함될 수 있는 클래스(엔티티의 필드처럼 사용됨)
@Getter
//@Setter
@NoArgsConstructor // VO에서는 비권장하지만 JPA의 Embeddable라면 넣어야 함
//@AllArgsConstructor
//@Builder 필드 선택적 입력으로 추천하지 않음

// TbBcode의 복합키 정의 클래스
// 복합키를 내부에서 안전하게 관리 전손하기 위한 바이트로 변환 = Serializable
public class TbBcodeId implements Serializable {
    @Column(name = "acode", nullable = false)
    private String acode;

    @Column(name = "bcode", nullable = false)
    private String bcode;

    // VO를 사용하여 불변성 강조
    public TbBcodeId(String acode, String bcode) {
        this.acode = acode;
        this.bcode = bcode;
    }
}
