package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByEmail(String email);

    Optional<Subscriber> findByUnsubscribeToken(String unsubscribeToken);
}