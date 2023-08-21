package com.example.profilematcher.service;

import com.example.profilematcher.dto.campaign.CampaignDto;
import com.example.profilematcher.dto.player.PlayerProfileDto;

import java.util.UUID;

public interface ProfileMatcherService {
     PlayerProfileDto matchAndUpdateProfile(UUID playerId, CampaignDto currentCampaign);
}
