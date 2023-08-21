package com.example.profilematcher.api;

import com.example.profilematcher.dto.player.PlayerProfileDto;
import com.example.profilematcher.service.CampaignService;
import com.example.profilematcher.service.ProfileMatcherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.example.profilematcher.constant.Constant.PLAYER_ID;
@Slf4j
@RestController
@AllArgsConstructor
public class ProfileMatcherController {

    private final ProfileMatcherService profileMatcherService;
    private final CampaignService campaignService;

    @GetMapping("/get_client_config/{player_id}")
    public ResponseEntity<PlayerProfileDto> getClientConfig(
            @PathVariable(value = PLAYER_ID) UUID playerId) {
        log.info("Get client config for player id: {}", playerId);
        return ResponseEntity.ok(profileMatcherService.matchAndUpdateProfile(playerId, campaignService.getCampaign()));

    }

}
