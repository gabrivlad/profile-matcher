package com.example.profilematcher.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_profiles")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PlayerProfile {
    @Id
    @Column(name = "player_id", unique = true, nullable = false)
    private UUID playerId;

    @Column(name = "credential")
    private String credential;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "last_session")
    private LocalDateTime lastSession;

    @Column(name = "total_spent")
    private Integer totalSpent;

    @Column(name = "total_refund")
    private Integer totalRefund;

    @Column(name = "total_transactions")
    private Integer totalTransactions;

    @Column(name = "last_purchase")
    private LocalDateTime lastPurchase;

    @Column(name = "active_campaigns")
    private List<String> activeCampaigns;

    @OneToMany(mappedBy = "playerProfile")
    private List<Device> devices;

    @Column(name = "level")
    private Integer level;

    @Column(name = "xp")
    private Integer xp;

    @Column(name = "total_playtime")
    private int totalPlaytime;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name = "birthdate")
    private LocalDateTime birthdate;

    @Column(name = "gender")
    private String gender;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Integer> inventory;
    @OneToOne
    private Clan clan;

    @Column(name = "_customfield")
    private String customField;


}

