package com.example.news.news_letter_back.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "newsletter")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK: admin_id → admin_user(id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminUser adminUser;

    @Column(name = "news_title", nullable = false, columnDefinition = "TEXT")
    private String newsTitle;

    @Column(name = "news_content_html", columnDefinition = "TEXT")
    private String newsContentHtml;

    @Column(name = "status_acode", nullable = false)
    private String statusAcode;

    @Column(name = "status_bcode", nullable = false)
    private String statusBcode;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "scheduled_at")
    private OffsetDateTime scheduledAt;

    @Column(name = "published_at")
    private OffsetDateTime publishedAt;
}