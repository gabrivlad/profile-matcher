package com.example.profilematcher.repository;


import com.example.profilematcher.model.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {
    Optional<PlayerProfile> findByPlayerId(UUID playerId);

}

