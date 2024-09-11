package com.toribro.space.repository.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.domain.entity.vo.space.SpaceThumbnail;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SpaceMapper {
    void enrollSpace(@Param("userNo") Long userNo, @Param("space")  Space space);
    void attachmentSave(@Param("spaceNo") int spaceNo, @Param("attachment") Attachment attachment);
    //List<SpaceThumbnail> getSpaces(RowBounds rowBounds);
    List<SpaceThumbnail> findSpaces(@Param("startRow")int startRow, @Param("endRow")int endRow);
    int getCount();
    List<Attachment> findAttachmentByNo(@Param("spaceNo") int spaceNo);
    SpaceDetail findSpaceByNo(@Param("spaceNo") int spaceNo);


    void updateSpace(@Param("spaceNo")int spaceNo, @Param("update") SpaceDto.UpdateDto update);
    void updateAttachment(@Param("spaceNo") int spaceNo, @Param("attachment") Attachment attachment);
    void deleteSpace(@Param("spaceNo") int spaceNo);
    //첨푸파일 정보는 제약조건에 의거 자동 삭제
}
