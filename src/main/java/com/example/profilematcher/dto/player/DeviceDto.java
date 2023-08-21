package com.example.profilematcher.dto.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DeviceDto {
    private Long id;
    private String model;
    private String carrier;
    private String firmware;
}
