package com.example.news.news_letter_back.infra;

import com.example.news.news_letter_back.entity.Subscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.BulkEmailDestination;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.SendBulkTemplatedEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SESServiceImpl implements SESService {
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
}
