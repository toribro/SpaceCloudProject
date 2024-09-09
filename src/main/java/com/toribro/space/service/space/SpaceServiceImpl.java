package com.toribro.space.service.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.domain.entity.space.vo.SpaceAttachment;
import com.toribro.space.repository.space.SpaceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceMapper spaceMapper;

    @Override
    @Transactional
    public int enroll(SpaceDto.EnrollDto enrollDto,   List<SpaceDto.SpaceAttachmentDto> fileInfos) {
        List<String> spaceKinds = enrollDto.getSpaceKind();
        String[] spaceKind = spaceKinds.toArray(new String[spaceKinds.size()]);
        String spaceValues="";
        if(spaceKind!=null){
            spaceValues=String.join(" ",spaceKind);
        }
        enrollDto.setSpaceKindValues(spaceValues);
        log.info("service로직처리:{}",spaceKinds);

        int result=-1;

        Space space=Space.builder()
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

        try{
            spaceMapper.enrollSpace(enrollDto.getUserNo(),space);
            int spaceNo=space.getSpaceNo();
            log.info("spaceNo: {}",spaceNo);
            for(SpaceDto.SpaceAttachmentDto fileInfo:fileInfos){

                Attachment attachment=Attachment.builder()
                        .fileLevel(fileInfo.getFileLevel())
                        .changeName(fileInfo.getChangeName())
                        .originName(fileInfo.getOriginName())
                        .filePath(fileInfo.getFilePath())
                        .build();
                spaceMapper.attachmentSave(spaceNo, attachment);
            }
            return result=1;
        }catch(MyBatisSystemException e){
            e.printStackTrace();
            exceptionFile(fileInfos);
        }
        catch(Exception e1){
            e1.printStackTrace();
            exceptionFile(fileInfos);
        }
       return result;
    }

    private static void exceptionFile(List<SpaceDto.SpaceAttachmentDto> fileInfos) {
        try{
            for(SpaceDto.SpaceAttachmentDto deleteFile: fileInfos){
                File file = new File(deleteFile.getFilePath() + deleteFile.getChangeName());
                if(file.exists()){
                     file.delete();
                    log.info("삭제성공");
                }else{
                    log.info("삭제실패");
                }
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            log.info("내부서버 에러 ,파일 삭제");
        }
    }

    @Override
    public List<SpaceAttachment> getSpaces() {

        try{
            return spaceMapper.getSpaces();

        }catch(MyBatisSystemException e){
            e.printStackTrace();
        }
       return null;
    }
}
