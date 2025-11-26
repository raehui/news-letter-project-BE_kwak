package com.example.news.news_letter_back.service;



import com.example.news.news_letter_back.entity.Subscriber;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriberService {

    public ResponseEntity<?> subscribe(String email);

    public ResponseEntity<?> unsubscribe(String token);
}
