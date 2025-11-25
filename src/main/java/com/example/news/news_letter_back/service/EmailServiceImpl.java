package com.example.news.news_letter_back.service;

import com.example.news.news_letter_back.entity.Subscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.datasource.mail.sender}")
    private String sender;

    private final Region region = Region.AP_NORTHEAST_2;

    @Override
    public void sendBulkTemplateEmail(List<Subscriber> subscribers, String title, String contents) {
        try (SesClient client = SesClient.builder().region(this.region).build()) {
            ObjectMapper mapper = new ObjectMapper();

            List<BulkEmailDestination> destinations = subscribers.stream().map(subscriber -> {
                String email = subscriber.getEmail();
                Map<String, String> map = new HashMap<>();
                map.put("name", email.split("@")[0]); // 예시로 이름은 이메일 앞부분

                String json;
                try {
                    json = mapper.writeValueAsString(map);
                } catch (Exception e) {
                    throw new RuntimeException("JSON 직렬화 오류", e);
                }

                return BulkEmailDestination.builder()
                        .destination(Destination.builder()
                                .toAddresses(email)
                                .build())
                        .replacementTemplateData(json)
                        .build();
            }).toList();

            SendBulkTemplatedEmailRequest request = SendBulkTemplatedEmailRequest.builder()
                    .source(sender)
                    // TODO: 템플릿 이름은 추가로 받을 수 있도록 수정해야 함
                    .template("MyTemplate")
//                    .template(templateName)
                    .defaultTemplateData("{}")
                    .destinations(destinations)
                    .build();

            client.sendBulkTemplatedEmail(request);
            System.out.println("Success");
        } catch (SesException e) {
            System.err.println("SES Bulk Error: " + e.awsErrorDetails().errorMessage());
            throw e;
        }
    }

//    @Override
//    public void sendEmail(String toAddress, String subject, String body) {
//        try (SesClient client = SesClient.builder()
//                .region(this.region)
//                .build()) {
//
//            Destination destination = Destination.builder()
//                    .toAddresses(toAddress)
//                    .build();
//
//            Content title = Content.builder()
//                    .data(subject)
//                    .charset("UTF-8")
//                    .build();
//
//            Content htmlContent = Content.builder()
//                    .data(body)
//                    .charset("UTF-8")
//                    .build();
//
//            Body emailBody = Body.builder()
//                    .html(htmlContent)
//                    .build();
//
//            Message message = Message.builder()
//                    .subject(title)
//                    .body(emailBody)
//                    .build();
//
//            SendEmailRequest request = SendEmailRequest.builder()
//                    .destination(destination)
//                    .message(message)
//                    .source(this.sender)
//                    .build();
//
//            client.sendEmail(request);
//        } catch (SesException e) {
//            System.err.println("SES Error: " + e.awsErrorDetails().errorMessage());
//            throw e;
//        }
//    }
}