package com.example.news.news_letter_back.controller;

import com.example.news.news_letter_back.dto.SubscriberRequest;
import com.example.news.news_letter_back.dto.UnsubscriberRequest;
import com.example.news.news_letter_back.service.SubscriberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SubscriberController {
    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriberRequest request) {
        return subscriberService.subscribe(request.getEmail());
    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestParam("token") String token) {
        return subscriberService.unsubscribe(token);
    }
}
