package com.example.news.news_letter_back.infra;

import com.example.news.news_letter_back.dto.news.EditEmailTemplateRequestDto;
import com.example.news.news_letter_back.entity.Subscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.CreateEmailTemplateRequest;
import software.amazon.awssdk.services.sesv2.model.EmailTemplateContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SESServiceImpl implements SESService {
    @Value("${spring.datasource.mail.sender}")
    private String sender;
    private final Region region = Region.AP_NORTHEAST_2;

    // V1: 이메일 발송용 클라이언트
    private SesClient createClient() {
        return SesClient.builder()
                .region(this.region)
                .build();
    }

    // V2: 템플릿 생성/관리용 클라이언트
    private SesV2Client createV2Client() {
        return SesV2Client.builder()
                .region(this.region)
                .build();
    }


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

    @Override
    public boolean editEmailTemplate(EditEmailTemplateRequestDto request) {
        try (SesClient client = createClient()) {

            String templateName = request.getTemplateName();
            String title = request.getTitle();
            String contents = request.getContents();

            Template template = Template.builder()
                    .templateName(templateName)
                    .subjectPart(title)
                    .htmlPart(contents)
                    .textPart(contents)
                    .build();

            UpdateTemplateRequest updateTemplateRequest = UpdateTemplateRequest.builder()
                    .template(template)
                    .build();

            client.updateTemplate(updateTemplateRequest);

            System.out.println("Template updated: " + templateName);

            return true;
        } catch (SesException e) {
            System.err.println("SES Update Error: " + e.awsErrorDetails().errorMessage());
            throw new RuntimeException("SES 템플릿 수정 실패: " + e.awsErrorDetails().errorMessage(), e);
        }
    }

    @Override
    public boolean createTemplate(String templateId, String title, String contents) {
        try (SesV2Client client = createV2Client()) {
            CreateEmailTemplateRequest request = CreateEmailTemplateRequest.builder()
                    .templateName(templateId)
                    .templateContent(EmailTemplateContent.builder()
                            .subject(title)
                            .html(contents)
                            .build())
                    .build();

            client.createEmailTemplate(request);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
