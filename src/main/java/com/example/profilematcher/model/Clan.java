package com.example.profilematcher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "clan")
public class Clan {
    @OneToOne
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerProfile playerProfile;
    @Id
    private String id;
    private String name;
}
