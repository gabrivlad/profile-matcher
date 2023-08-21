package com.example.profilematcher.service.impl;

import com.example.profilematcher.dto.campaign.CampaignDto;
import com.example.profilematcher.dto.player.PlayerProfileDto;
import com.example.profilematcher.exception.ProfileNotFoundException;
import com.example.profilematcher.model.PlayerProfile;
import com.example.profilematcher.repository.PlayerProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileMatcherServiceImplTest {

    @Mock
    private PlayerProfileRepository playerProfileRepository;

    @InjectMocks
    private ProfileMatcherServiceImpl profileMatcherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMatchAndUpdateProfile_ProfileNotFound() {
        UUID playerId = UUID.randomUUID();
        CampaignDto campaignDto = new CampaignDto();
        when(playerProfileRepository.findByPlayerId(playerId)).thenReturn(Optional.empty());

        assertThrows(ProfileNotFoundException.class, () -> profileMatcherService.matchAndUpdateProfile(playerId, campaignDto));

        verify(playerProfileRepository, times(1)).findByPlayerId(playerId);
    }

    @Test
    void testMatchAndUpdateProfile_ProfileIsNotMatching() {
        UUID playerId = UUID.randomUUID();
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setName("Test Campaign");

        PlayerProfile playerProfileEntity = new PlayerProfile();
        playerProfileEntity.setPlayerId(playerId);
        playerProfileEntity.setActiveCampaigns(new ArrayList<>());

        PlayerProfileDto playerProfileDto = new PlayerProfileDto();
        playerProfileDto.setPlayerId(playerId);

        when(playerProfileRepository.findByPlayerId(playerId)).thenReturn(Optional.of(playerProfileEntity));
        when(playerProfileRepository.save(playerProfileEntity)).thenReturn(playerProfileEntity);

        PlayerProfileDto result = profileMatcherService.matchAndUpdateProfile(playerId, campaignDto);

        assertNotNull(result);
        assertEquals(playerId, result.getPlayerId());
        assertFalse(result.getActiveCampaigns().contains(campaignDto.getName()));

        verify(playerProfileRepository, times(1)).findByPlayerId(playerId);
        verify(playerProfileRepository, times(0)).save(playerProfileEntity);
    }


}
