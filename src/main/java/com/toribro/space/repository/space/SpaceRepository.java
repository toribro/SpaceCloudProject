package com.toribro.space.repository.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.service.space.SpaceService;

import java.util.List;

public interface SpaceRepository {
    void enroll(SpaceDto.EnrollDto enrollDto, SpaceDto.SpaceAttachmentDto attachmentDto);
    List<SpaceService> spaces();
}
