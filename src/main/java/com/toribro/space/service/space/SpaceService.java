package com.toribro.space.service.space;

import com.toribro.space.domain.dto.space.SpaceDto;

import java.util.List;

public interface SpaceService {
    void enroll(SpaceDto.EnrollDto enrollDto,SpaceDto.SpaceAttachmentDto attachmentDto);
    List<SpaceService> spaces();
}
