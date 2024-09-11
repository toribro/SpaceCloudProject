package com.toribro.space.service.space;

import com.toribro.space.common.PageInfo;
import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.vo.space.SpaceAttachment;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import com.toribro.space.repository.space.SpaceMapper;
import com.toribro.space.repository.space.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;

import java.io.File;
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
    public List<SpaceAttachment> getSpaces(PageInfo pageInfo) {
        return spaceRepository.getSpaces(pageInfo);
    }

    @Override
    public int getCount() {
        return spaceRepository.getCount();
    }

    @Override
    public SpaceDetail getSpaceByOne(int spaceNo) {
         return spaceRepository.getSpaceByOne(spaceNo);

    }
}
