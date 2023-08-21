package com.example.profilematcher.service.impl;

import com.example.profilematcher.dto.campaign.*;
import com.example.profilematcher.service.CampaignService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CampaignServiceImpl implements CampaignService {
    public CampaignDto getCampaign() {
        return CampaignDto.builder()
                .game("mygame")
                .name("mycampaign")
                .priority(10.5)
                .matchers(MatcherDto.builder()
                        .level(LevelDto.builder()
                                .min(1)
                                .max(3)
                                .build())
                        .has(HasDto.builder()
                                .country(Set.of("US", "RO", "CA"))
                                .items(Set.of("item_1"))
                                .build())
                        .doesNotHave(DoesNotHaveDto.builder()
                                .items(Set.of("item_4"))
                                .build())
                        .build())
                .startDate(LocalDateTime.of(2022,01,25,00,00,00))
                .endDate(LocalDateTime.of(2022,02,25,00,00,00))
                .enabled(true)
                .lastUpdated(LocalDateTime.of(2021,07,13,11,46,58))
                .build();
    }
}
