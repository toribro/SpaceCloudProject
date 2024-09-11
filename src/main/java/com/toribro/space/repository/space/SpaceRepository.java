package com.toribro.space.repository.space;

import com.toribro.space.common.PageInfo;
import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.vo.space.SpaceThumbnail;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;

import java.util.List;

public interface SpaceRepository {
    int enroll(SpaceDto.EnrollDto enrollDto, List<SpaceDto.SpaceAttachmentDto> fileInfo);
    List<SpaceThumbnail> findSpaces(PageInfo pagination);
    SpaceThumbnail findSpaceThumbnailByNo(int spaceNo);
    List<Attachment> findAttachmentByNo(int spaceNo);
    int getCount();
    SpaceDetail findSpaceByNo(int spaceNo);
    int updateSpace(int spaceNo,SpaceDto.UpdateDto updateDto,List<SpaceDto.SpaceAttachmentDto> fileInfo);
    int deleteSpace(int spaceNo);
}
