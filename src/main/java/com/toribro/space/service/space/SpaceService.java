package com.toribro.space.service.space;

import com.toribro.space.common.PageInfo;
import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.vo.space.SpaceThumbnail;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;

import java.util.List;

public interface SpaceService {
    //공간등록
    int enroll(SpaceDto.EnrollDto enrollDto,  List<SpaceDto.SpaceAttachmentDto> fileInfo);

    //공간메인이미지(썸네일)리스트
    List<SpaceThumbnail> findSpaces(PageInfo pagination);
    //공간메인이미지
    SpaceThumbnail findSpaceThumbnailByNo(int spaceNo);

    //첨부파일목록
    List<Attachment> findAttachmentByNo(int spaceNo);

    //등록된 공간 개수
    int getCount();

    //공간상세정보
    SpaceDetail findSpaceByNo(int spaceNo);

    //공간정보업데이트
    int updateSpace(int spaceNo,SpaceDto.UpdateDto updateDto,List<SpaceDto.SpaceAttachmentDto> fileInfo);

    //등록된 공간 삭제
    int deleteSpace(int spaceNo);
}
