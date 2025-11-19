package com.example.news.news_letter_back.config;

import com.example.news.news_letter_back.entity.TbAcode;
import com.example.news.news_letter_back.entity.TbBcode;
import com.example.news.news_letter_back.entity.TbBcodeId;
import com.example.news.news_letter_back.repository.AcodeRepository;
import com.example.news.news_letter_back.repository.BcodeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CodeInitializer {

    private final AcodeRepository acodeRepository;
    private final BcodeRepository bCodeRepository;

    public CodeInitializer(AcodeRepository acodeRepository, BcodeRepository bCodeRepository) {
        this.acodeRepository = acodeRepository;
        this.bCodeRepository = bCodeRepository;
    }

    @PostConstruct
    public void init() {
        // Acode 정의
        List<AcodeData> acodes = List.of(
            new AcodeData("SUBSCRIBE", "구독상태"),
            new AcodeData("POST", "홈페이지_글상태"),
            new AcodeData("EMAIL", "메일용_글상태")
        );

        // Bcode 정의
        List<BcodeData> bcodes = List.of(
            new BcodeData("SUBSCRIBE", "SUB", "구독"),
            new BcodeData("SUBSCRIBE", "UNSUB", "비구독"),
            new BcodeData("POST", "PUBLISHED", "발행"),
            new BcodeData("POST", "DRAFT", "임시저장"),
            new BcodeData("POST", "DELETED", "삭제"),
            new BcodeData("EMAIL", "PUBLISHED", "발행"),
            new BcodeData("EMAIL", "DRAFT", "임시저장"),
            new BcodeData("EMAIL", "DELETED", "삭제")
        );

        // Acode 삽입
        for (AcodeData acodeData : acodes) {
            if (!acodeRepository.existsById(acodeData.acode())) {
                acodeRepository.save(new TbAcode(acodeData.acode(), acodeData.aname(), "NO"));
            }
        }

        // Bcode 삽입
        for (BcodeData bcodeData : bcodes) {
            TbBcodeId id = new TbBcodeId(bcodeData.acode(), bcodeData.bcode());
            if (!bCodeRepository.existsById(id)) {
                acodeRepository.findById(bcodeData.acode()).ifPresent(acodeEntity -> {
                    bCodeRepository.save(TbBcode.builder()
                        .id(id)
                        .bname(bcodeData.bname())
                        .deleted("NO")
                        .acode(acodeEntity)
                        .build());
                });
            }
        }
    }

    // 내부 레코드 정의
    record AcodeData(String acode, String aname) {}
    record BcodeData(String acode, String bcode, String bname) {}
}