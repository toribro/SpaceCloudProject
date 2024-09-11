package com.toribro.space.repository.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.member.Member;
import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import com.toribro.space.domain.entity.vo.space.SpaceThumbnail;
import com.toribro.space.repository.member.MemberMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class SpaceRepositoryImplTest {

    @Autowired
    private SpaceMapper spaceMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Test
    void enroll() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("19980416",formatter);

        Member member = Member.builder()
                .userId("tes2")
                .userPwd("test2")
                .userName("test2")
                .nickName("test2")
                .gender("M")
                .email("test2@test2.com")
                .phone("01011112223")
                .birth(java.sql.Date.valueOf(birth))
                .build();
        memberMapper.save(member);

        Space space= Space.builder()
                .spaceName("test")
                .spaceKind("test")
                .spaceOneIntroduce("test")
                .spaceIntroduce("test")
                .spaceTag("test")
                .spaceInformation("test")
                .spaceCaution("test")
                .spaceAddress("test")
                .spaceDetailAddress("test")
                .spacePrice(3)
                .spaceLocation("test")
                .spaceTel("1234-5678")
                .spaceCapacity(3)
                .build();
        space.setMember(member);
        spaceMapper.enrollSpace(member.getUserNo(), space);
        log.info("space:{}",space);

        SpaceDetail findSpace =spaceMapper.findSpaceByNo(space.getSpaceNo());
        assertThat(findSpace.getUserNo()).isEqualTo(member.getUserNo());


    }

    @Test
    void findSpaces() {
    }

    @Test
    void findSpaceThumbnailByNo() {
    }

    @Test
    void findAttachmentByNo() {
    }

    @Test
    void findSpaceByNo() {
    }

    @Test
    void updateSpace() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("19980416",formatter);

        Member member = Member.builder()
                .userId("tes2")
                .userPwd("test2")
                .userName("test2")
                .nickName("test2")
                .gender("M")
                .email("test2@test2.com")
                .phone("01011112223")
                .birth(java.sql.Date.valueOf(birth))
                .build();
        memberMapper.save(member);

        Space space= Space.builder()
                .spaceName("test")
                .spaceKind("test")
                .spaceOneIntroduce("test")
                .spaceIntroduce("test")
                .spaceTag("test")
                .spaceInformation("test")
                .spaceCaution("test")
                .spaceAddress("test")
                .spaceDetailAddress("test")
                .spacePrice(3)
                .spaceLocation("test")
                .spaceTel("1234-5678")
                .spaceCapacity(3)
                .build();
        spaceMapper.enrollSpace(member.getUserNo(),space);

        //업데이트
        SpaceDto.UpdateDto updateSpace=new SpaceDto.UpdateDto();
        updateSpace.setSpaceName("");
        updateSpace.setSpaceKindValues("");
        updateSpace.setSpaceOneIntroduce("");
        updateSpace.setSpaceIntroduce("");
        updateSpace.setSpaceTags("updateTest");
        updateSpace.setSpaceAddress("");
        updateSpace.setSpaceDetailAddress("");
        updateSpace.setSpaceTel("");
        updateSpace.setSpacePrice(0);
        updateSpace.setSpaceCapacity(0);
        updateSpace.setSpaceInformation("");
        updateSpace.setSpaceCaution("");
        updateSpace.setSpaceLocation("");

        spaceMapper.updateSpace(space.getSpaceNo(),updateSpace);

        //결과
        SpaceDetail findSpace = spaceMapper.findSpaceByNo(space.getSpaceNo());
        String updatedSpaceResult1=findSpace.getSpaceTag();
        String updatedSpaceResult2=findSpace.getSpaceLocation();

        //테스트
        assertThat(updatedSpaceResult1).isEqualTo("updateTest");
        assertThat(updatedSpaceResult2).isEqualTo("test");


    }

    @Test
    void deleteSpace() {
    }
}