package com.toribro.space.repository.space;

import com.toribro.space.common.PageInfo;
import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.domain.entity.vo.space.SpaceThumbnail;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SpaceRepositoryImpl implements  SpaceRepository {

    private final SpaceMapper spaceMapper;

    @Override
    public int enroll(SpaceDto.EnrollDto enrollDto, List<SpaceDto.SpaceAttachmentDto> fileInfos) {
        int result = -1;

        Space space = Space.builder()
                .spaceName(enrollDto.getSpaceName())
                .spaceKind(enrollDto.getSpaceKindValues())
                .spaceOneIntroduce(enrollDto.getSpaceOneIntroduce())
                .spaceIntroduce(enrollDto.getSpaceIntroduce())
                .spaceTag(enrollDto.getSpaceTags())
                .spaceAddress(enrollDto.getSpaceAddress())
                .spaceTel(enrollDto.getSpaceTel())
                .spaceCapacity(enrollDto.getSpaceCapacity())
                .spaceCaution(enrollDto.getSpaceCaution())
                .spaceInformation(enrollDto.getSpaceInformation())
                .spaceDetailAddress(enrollDto.getSpaceDetailAddress())
                .spaceLocation(enrollDto.getSpaceLocation())
                .spacePrice(enrollDto.getSpacePrice())
                .build();

        try {
            spaceMapper.enrollSpace(enrollDto.getUserNo(), space);
            int spaceNo = space.getSpaceNo();
            log.info("spaceNo: {}", spaceNo);
            for (SpaceDto.SpaceAttachmentDto fileInfo : fileInfos) {

                Attachment attachment = Attachment.builder()
                        .fileLevel(fileInfo.getFileLevel())
                        .changeName(fileInfo.getChangeName())
                        .originName(fileInfo.getOriginName())
                        .filePath(fileInfo.getFilePath())
                        .build();
                spaceMapper.attachmentSave(spaceNo, attachment);
            }
            return result = 1;
        } catch (MyBatisSystemException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return result;
    }


    @Override
    public List<SpaceThumbnail> findSpaces(PageInfo pageInfo) {

        //마이 바티스 rowbound를 사용하여 페이징 처리
//        int offset=(pageInfo.getCurrentPage()-1)* pageInfo.getBoardLimit();
//        RowBounds rowBounds=new RowBounds(offset,pageInfo.getBoardLimit());

        //생으로 페이징 처리
        int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
        int endRow = startRow + pageInfo.getBoardLimit() - 1;


        try {
            return spaceMapper.findSpaces(startRow, endRow);
//            return spaceMapper.getSpaces(rowBounds);

        } catch (MyBatisSystemException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SpaceThumbnail findSpaceThumbnailByNo(int spaceNo) {
        return null;
    }

    @Override
    public List<Attachment> findAttachmentByNo(int spaceNo) {
        return spaceMapper.findAttachmentByNo(spaceNo);
    }

    @Override
    public int getCount() {
        return spaceMapper.getCount();
    }

    @Override
    public SpaceDetail findSpaceByNo(int spaceNo) {

        SpaceDetail findSpace = spaceMapper.findSpaceByNo(spaceNo);
        List<Attachment> spaceAttachment = spaceMapper.findAttachmentByNo(spaceNo);
        findSpace.setAttachments(spaceAttachment);

        return findSpace;

    }

    @Override
    public int updateSpace(int spaceNo, SpaceDto.UpdateDto updateDto, List<SpaceDto.SpaceAttachmentDto> fileInfos) {

        int result = -1;
        try {
            spaceMapper.updateSpace(spaceNo, updateDto);
            for (SpaceDto.SpaceAttachmentDto fileInfo : fileInfos) {

                Attachment attachment = Attachment.builder()
                        .fileLevel(fileInfo.getFileLevel())
                        .changeName(fileInfo.getChangeName())
                        .originName(fileInfo.getOriginName())
                        .filePath(fileInfo.getFilePath())
                        .build();
                spaceMapper.updateAttachment(spaceNo, attachment);
            }
            result = 1;
        } catch (MyBatisSystemException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteSpace(int spaceNo) {
        int result = -1;
        try {
            spaceMapper.deleteSpace(spaceNo);//제약조건이 cacade이므로 첨부파일 정보는 자동으로 삭제
            result = 1;
            } catch (MyBatisSystemException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
          return result;
        }
    }