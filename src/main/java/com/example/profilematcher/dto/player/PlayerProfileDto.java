package com.example.profilematcher.dto.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerProfileDto {
    private UUID playerId;

    private String credential;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime lastSession;

    private Integer totalSpent;

    private Integer totalRefund;

    private Integer totalTransactions;

    private LocalDateTime lastPurchase;

    private List<String> activeCampaigns;

    private List<DeviceDto> devices;

    private Integer level;

    private Integer xp;

    private int totalPlaytime;

    private String country;

    private String language;

    private LocalDateTime birthdate;

    private String gender;

    private Map<String, Integer> inventory;

    private ClanDto clan;

    private String customField;
}
