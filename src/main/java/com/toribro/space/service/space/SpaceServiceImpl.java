package com.toribro.space.service.space;

import com.toribro.space.common.PageInfo;
import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.vo.space.SpaceThumbnail;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import com.toribro.space.repository.space.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;


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

        int result = spaceRepository.enroll(enrollDto, fileInfos);
        if(result<0){
            deleteExceptionFile(fileInfos);
        }
        return result;
    }

    private static void deleteExceptionFile(List<SpaceDto.SpaceAttachmentDto> fileInfos) {
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

    private static void deleteFile(List<Attachment> fileInfos) {
        try{
            for(Attachment deleteFile: fileInfos){
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
            log.info("파일 삭제");
        }
    }

    @Override
    public List<SpaceThumbnail> findSpaces(PageInfo pageInfo) {
        return spaceRepository.findSpaces(pageInfo);
    }

    @Override
    public SpaceThumbnail findSpaceThumbnailByNo(int spaceNo) {
        return null;
    }

    @Override
    public List<Attachment> findAttachmentByNo(int spaceNo) {
        return List.of();
    }

    @Override
    public int getCount() {
        return spaceRepository.getCount();
    }

    @Override
    public SpaceDetail findSpaceByNo(int spaceNo) {
         return spaceRepository.findSpaceByNo(spaceNo);

    }

    @Override
    public int updateSpace(int spaceNo,SpaceDto.UpdateDto updateDto,List<SpaceDto.SpaceAttachmentDto> fileInfo) {

        List<String> spaceKinds = updateDto.getSpaceKind();
        String[] spaceKind = spaceKinds.toArray(new String[spaceKinds.size()]);
        String spaceValues="";
        if(spaceKind!=null){
            spaceValues=String.join(" ",spaceKind);
        }
        updateDto.setSpaceKindValues(spaceValues);
        log.info("update service로직처리:{}",spaceKinds);


        //업데이트 하기전에 원래 저장되어있던 파일 가져오기
        List<Attachment> previousFile = spaceRepository.findAttachmentByNo(spaceNo);

        int result = spaceRepository.updateSpace(spaceNo, updateDto, fileInfo);

        if(result<0){//실패시 원래 들어오려던 파일 삭제
            deleteExceptionFile(fileInfo);
        }else{//업데이트가 완료되었으면 서버에 원래있던 파일 삭제(사진이 업데이트 될때만)
            if(result>=2){
                deleteFile(previousFile);
            }

        }
        return  result;
    }

    @Override
    public int deleteSpace(int spaceNo) {
        List<Attachment> previousFile = spaceRepository.findAttachmentByNo(spaceNo);
        int result = spaceRepository.deleteSpace(spaceNo);

        if(result>0){//삭제성공시 서버에서 파일 지운다.
           deleteFile(previousFile);
        }
        return result;
    }
}
