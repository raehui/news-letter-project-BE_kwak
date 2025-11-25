package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.entity.Subscriber;

import java.util.List;

public interface EmailService {
    public void sendBulkTemplateEmail(List<Subscriber> subscribers, String title, String contents);
}
