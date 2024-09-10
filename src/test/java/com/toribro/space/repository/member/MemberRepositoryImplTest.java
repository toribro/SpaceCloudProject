package com.toribro.space.repository.member;

import com.toribro.space.domain.entity.member.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//파일 경로에 한글이 포함되어있으면 test코드가 안돌아갈 수있다.
//원래는 intellig 로 바꾸면 되었는데 안된다.

@Transactional
@Slf4j
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberMapper memberMapper;

    //테스트 코드
    //테스트 데이터베이스 별도로 생성
    @Test
    @Commit
    //테스트 데이터 삽입
    void save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("19980416",formatter);

        Member member = Member.builder()
                .userId("test")
                .userPwd("test")
                .userName("test")
                .nickName("test")
                .gender("M")
                .email("test@test.com")
                .phone("01011112222")
                .birth(java.sql.Date.valueOf(birth))
                .build();
        memberMapper.save(member);

        Member findById =  memberMapper.findByNo(member.getUserNo());
        assertThat(findById ).isEqualTo(member);//equals hascode

    }

    @Test
    void findMemberById() {



    }

    @Test
    void findMember() {


    }

    @Test
    void updateMember() {
        Member member = Member.builder()
                .userNo(1L)
                .userId("test")
                .userPwd("test")
                .userName("test")
                .nickName("test")
                .nickName("test")
                .build();

    }

    @Test
    void out() {
    }

}