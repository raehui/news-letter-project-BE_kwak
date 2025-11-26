package com.example.news.news_letter_back.infra;

import com.example.news.news_letter_back.dto.news.EditEmailTemplateRequestDto;
import com.example.news.news_letter_back.entity.Subscriber;

import java.util.List;

public interface SESService {
    public void sendBulkTemplateEmail(List<Subscriber> subscribers, String title, String contents);

    public boolean editEmailTemplate(EditEmailTemplateRequestDto request);
}
