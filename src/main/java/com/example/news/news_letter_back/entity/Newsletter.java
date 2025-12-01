package com.example.news.news_letter_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "newsletter")
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsletterId;

    // 지연로딩으로 필요할 때, DB에서 가져옴
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminUser adminUser;

    @Column(name = "news_title", nullable = false)
    private String newsTitle;

    @Column(name = "news_content_html", columnDefinition = "TEXT")   // 이미지 URL도 들어가므로 TEXT 타입 유지
    private String contentHtml;

    @Builder.Default
    @Column(name = "status_acode", nullable = false)
    private String statusAcode = "EMAIL";

    @Builder.Default
    @Column(name = "status_bcode", nullable = false)
    private String statusBcode = "DRAFT";

    // a_code, b_code 테이블 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 복합 왜래키(acode, bcode) 매핑
    // 카테고리 테이블에 없는 acode, bcode에 있는 값만 들어갈 수 잇음
    @JoinColumns({
        @JoinColumn(name = "status_acode", referencedColumnName = "acode", insertable = false, updatable = false),
        @JoinColumn(name = "status_bcode", referencedColumnName = "bcode", insertable = false, updatable = false)
    })
    private TbBcode statusCode;

    // DB에서 트리거로 자동으로 수정날짜 들어간다.

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt; // ???

    // DB에서 트리거로 자동으로 수정날짜 들어간다.
   
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    @Column(name = "scheduled_at")
    private OffsetDateTime scheduledAt;

    @Column(name = "published_at")
    private OffsetDateTime publishedAt;

    // 제목의 여부 확인
    public void istitle() {
        if (this.newsTitle == null || this.newsTitle.length() == 0) {
            throw new IllegalArgumentException("제목이 비었습니다!");
        }
    }
    // 발행하기
    public void publish(){
        this.statusBcode="PUBLISHED";
        this.publishedAt = OffsetDateTime.now();
    }

    // 수정하기
    public void update(String newstitle, String contentHtml, String statusBcode) {
        this.newsTitle = newstitle;
        this.contentHtml = contentHtml;
        this.statusBcode = statusBcode;
        this.updatedAt = OffsetDateTime.now();
    }

}
