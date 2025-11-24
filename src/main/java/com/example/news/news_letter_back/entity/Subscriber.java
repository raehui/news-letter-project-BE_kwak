package com.example.news.news_letter_back.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "subscriber")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriber_id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "status_acode", nullable = false)
    private String statusAcode = "SUBSCRIBE";

    @Column(name = "status_bcode", nullable = false)
    private String statusBcode = "SUB";

    @Column(name = "unsubscribe_token", nullable = false, unique = true)
    private String unsubscribeToken;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt; // ???

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    // a_code, b_code 테이블 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 복합 왜래키(acode, bcode) 매핑
    // 카테고리 테이블에 없는 acode, bcode에 있는 값만 들어갈 수 잇음
    @JoinColumns({
            @JoinColumn(name = "status_acode", referencedColumnName = "acode", insertable = false, updatable = false),
            @JoinColumn(name = "status_bcode", referencedColumnName = "bcode", insertable = false, updatable = false)
    })
    private TbBcode statusCode;



}

