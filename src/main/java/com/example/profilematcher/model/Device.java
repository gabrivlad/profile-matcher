package com.example.profilematcher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "device")
public class Device {
    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerProfile playerProfile;
    @Id
    private Long id;
    private String model;
    private String carrier;
    private String firmware;
}

