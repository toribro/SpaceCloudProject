package com.toribro.space.service.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.space.Space;

import java.util.List;
import java.util.Map;

public interface SpaceService {
    int enroll(SpaceDto.EnrollDto enrollDto,  List<SpaceDto.SpaceAttachmentDto> fileInfo);
    List<Space> spaces();
}
