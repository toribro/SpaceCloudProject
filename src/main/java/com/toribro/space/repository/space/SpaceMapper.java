package com.toribro.space.repository.space;

import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.space.Space;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpaceMapper {
    void enrollSpace(Space space);
    void attachmentSave(@Param("spaceNo") int spaceNo, Attachment attachment);
}
