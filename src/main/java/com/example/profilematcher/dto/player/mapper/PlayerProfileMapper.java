package com.example.profilematcher.dto.player.mapper;

import com.example.profilematcher.dto.player.DeviceDto;
import com.example.profilematcher.dto.player.PlayerProfileDto;
import com.example.profilematcher.model.Device;
import com.example.profilematcher.model.PlayerProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface PlayerProfileMapper {

    PlayerProfileMapper INSTANCE = Mappers.getMapper(PlayerProfileMapper.class);

    @Mapping(source = "devices", target = "devices")
    PlayerProfileDto toPlayerProfileDto(PlayerProfile playerProfile);

    DeviceDto toDeviceDto(Device device);

    List<DeviceDto> toDeviceDtoList(List<Device> devices);
}
