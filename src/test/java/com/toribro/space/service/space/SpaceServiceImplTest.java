package com.toribro.space.service.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.member.Member;
import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.repository.member.MemberRepository;
import com.toribro.space.repository.space.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Slf4j
@SpringBootTest
class SpaceServiceImplTest {

    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
     private MemberRepository memberRepository;


    @Test
    void enroll() {


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
    }

    @Test
    void deleteSpace() {
    }
}