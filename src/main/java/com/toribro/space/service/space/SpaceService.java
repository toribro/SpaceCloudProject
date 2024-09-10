package com.toribro.space.service.space;

import com.toribro.space.common.PageInfo;
import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.domain.entity.vo.space.SpaceAttachment;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;

import java.util.List;

public interface SpaceService {
    int enroll(SpaceDto.EnrollDto enrollDto,  List<SpaceDto.SpaceAttachmentDto> fileInfo);
    List<SpaceAttachment> getSpaces(PageInfo pagination);

    int getCount();

    SpaceDetail getSpaceByOne(int spaceNo);
}
