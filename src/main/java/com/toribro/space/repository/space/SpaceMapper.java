package com.toribro.space.repository.space;

import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.domain.entity.vo.space.SpaceAttachment;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SpaceMapper {
    void enrollSpace(@Param("userNo") Long userNo, @Param("space")  Space space);
    void attachmentSave(@Param("spaceNo") int spaceNo, @Param("attachment") Attachment attachment);
    List<SpaceAttachment> getSpaces(RowBounds rowBounds);
    List<SpaceAttachment> getSpaces(int startRow, int endRow);
    int getCount();

    List<Attachment> getAttachment(@Param("spaceNo") int spaceNo);
    SpaceDetail getSpace(@Param("spaceNo") int spaceNo);
}
