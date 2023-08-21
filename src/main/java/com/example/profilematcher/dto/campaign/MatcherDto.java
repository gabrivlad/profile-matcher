package com.example.profilematcher.dto.campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatcherDto {
    private LevelDto level;
    private HasDto has;
    private DoesNotHaveDto doesNotHave;
}
