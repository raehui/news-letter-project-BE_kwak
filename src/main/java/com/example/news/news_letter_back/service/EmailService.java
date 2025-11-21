package com.example.news.news_letter_back.service;

public interface EmailService {
    public void sendEmail(String toAddress, String subject, String body);
}
