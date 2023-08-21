package com.example.profilematcher.dto.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClanDto {
   private Integer id;
   private String name;
}
