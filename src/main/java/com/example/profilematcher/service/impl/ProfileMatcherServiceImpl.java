package com.example.profilematcher.service.impl;

import com.example.profilematcher.dto.campaign.CampaignDto;
import com.example.profilematcher.dto.campaign.LevelDto;
import com.example.profilematcher.dto.campaign.MatcherDto;
import com.example.profilematcher.dto.player.PlayerProfileDto;
import com.example.profilematcher.dto.player.mapper.PlayerProfileMapper;
import com.example.profilematcher.exception.ProfileNotFoundException;
import com.example.profilematcher.model.PlayerProfile;
import com.example.profilematcher.repository.PlayerProfileRepository;
import com.example.profilematcher.service.ProfileMatcherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ProfileMatcherServiceImpl implements ProfileMatcherService {
    private final PlayerProfileRepository playerProfileRepository;

    public PlayerProfileDto matchAndUpdateProfile(UUID playerId, CampaignDto currentCampaign) {
        log.info("Match and update profile for player id: {}", playerId);
        Optional<PlayerProfile> playerProfileOptional = playerProfileRepository.findByPlayerId(playerId);

        if (playerProfileOptional.isEmpty()) {
            throw new ProfileNotFoundException("Player profile not found");
        } else {
            PlayerProfile playerProfileEntity = playerProfileOptional.get();
            PlayerProfileDto playerProfile = PlayerProfileMapper.INSTANCE.toPlayerProfileDto(playerProfileEntity);
            if (isProfileMatching(playerProfile, currentCampaign)) {
                addActiveCampaign(playerProfileEntity, currentCampaign.getName());
              return PlayerProfileMapper.INSTANCE.toPlayerProfileDto(playerProfileRepository.save(playerProfileEntity));
            }
            return playerProfile;
        }
    }

    private boolean isProfileMatching(PlayerProfileDto playerProfile, CampaignDto campaign) {
        log.info("Check if player profile is matching with campaign: {}", campaign.getName());
        if (campaign.getMatchers() == null) {
            return false;
        }
        return (doesPlayerMeetLevelCriteria(playerProfile.getLevel(),campaign.getMatchers())
                && doesPlayerMeetHasAttributesCriteria(playerProfile.getInventory().keySet(),playerProfile.getCountry(),campaign.getMatchers())
                && doesPlayerMeetHasNotAttributes(playerProfile.getInventory().keySet(),campaign.getMatchers()));
        }

    private void addActiveCampaign(PlayerProfile playerProfileEntity, String campaignName) {
        log.info("Add active campaign: {}", campaignName);
        List<String> activeCampaigns = playerProfileEntity.getActiveCampaigns();
        if (activeCampaigns.isEmpty() ||  !activeCampaigns.contains(campaignName)) {
            activeCampaigns.add(campaignName);
            playerProfileEntity.setActiveCampaigns(activeCampaigns);
        }
    }

    private boolean doesPlayerMeetLevelCriteria(Integer playerLevel, MatcherDto matcherDto) {
        log.info("Check if player level = {} is matching with campaign level = {}", playerLevel, matcherDto.getLevel());
        LevelDto levelDto = matcherDto.getLevel();
        Integer min = levelDto.getMin();
        Integer max = levelDto.getMax();
        return playerLevel >= min && playerLevel <= max;
    }
    private boolean doesPlayerMeetHasAttributesCriteria(Set<String> playerInventoryAttributes, String playerCountry, MatcherDto matcherDto) {
        log.info("Check if player inventory attributes = {} and country = {} are matching with campaign attributes = {} and country = {}", playerInventoryAttributes, playerCountry, matcherDto.getHas().getItems(), matcherDto.getHas().getCountry());
        Set<String> campaignItems = matcherDto.getHas().getItems();
        Set<String> campaignCountries = matcherDto.getHas().getCountry();
        return playerInventoryAttributes.containsAll(campaignItems) && campaignCountries.contains(playerCountry);

    }
    private boolean doesPlayerMeetHasNotAttributes(Set<String> playerItems, MatcherDto matcherDto) {
        log.info("Check if player inventory attributes = {} are  not matching with  hasNot campaign  attributes = {}", playerItems, matcherDto.getDoesNotHave().getItems());
        Set<String> doesNotHaveItems = matcherDto.getDoesNotHave().getItems();
        return !playerItems.containsAll(doesNotHaveItems);
    }


}
