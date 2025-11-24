package com.example.news.news_letter_back.entity;

import com.example.news.news_letter_back.dto.post.PostRequestDto;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminUser adminUser;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")   // 이미지 URL도 들어가므로 TEXT 타입 유지
    private String contentHtml;

    @Builder.Default
    @Column(name = "status_acode", nullable = false)
    private String statusAcode = "POST";

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
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt; // ???

    // DB에서 트리거로 자동으로 수정날짜 들어간다.
    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "published_at")
    private OffsetDateTime publishedAt;

    // 제목의 여부 확인
    public void istitle(){
        if(this.title == null||this.title.length()==0){
            throw new IllegalArgumentException("제목이 비었습니다!");
        }
    }
    // 발행하기
    public void publish(){
        this.statusBcode="PUBLISHED";
        this.publishedAt = OffsetDateTime.now();
    }
    // 수정하기
    public void update(String title, String contentHtml) {
        this.title = title;
        this.contentHtml = contentHtml;
        this.updatedAt = OffsetDateTime.now();
    }


}
