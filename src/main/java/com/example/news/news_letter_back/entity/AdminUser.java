package com.example.news.news_letter_back.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Getter // 모든 필드에 대한 값을 Getter 메서드 자동 생성
@Setter // 모든 필드에 대한 값을 Setter 메서드 자동 생성
@Builder // 빌더 패턴으로 객체 생성 가능
@Entity
@Table(name = "admin_user")
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 매핑
    private Long adminId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // 생성 시 현재 시간 자동 삽입
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    // 수정 시 현재 시간 자동 삽입
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(nullable = false)
    private String role;


}
