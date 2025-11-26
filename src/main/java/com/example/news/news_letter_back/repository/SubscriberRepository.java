package com.example.news.news_letter_back.repository;

import com.example.news.news_letter_back.entity.Subscriber;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    public Optional<Subscriber> findByEmail(String email);

    public Optional<Subscriber> findByUnsubscribeToken(String unsubscribeToken);
}
