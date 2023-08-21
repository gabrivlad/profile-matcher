package com.example.profilematcher.dto.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignDto {
    private String game;
    private String name;
    private Double priority;
    private MatcherDto matchers;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean enabled;
    private LocalDateTime lastUpdated;
}
