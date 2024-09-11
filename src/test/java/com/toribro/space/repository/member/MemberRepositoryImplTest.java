package com.toribro.space.repository.member;

import com.toribro.space.domain.dto.member.MemberDto;
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

        Member findById =  memberMapper.findMemberByNo(member.getUserNo());
        assertThat(findById ).isEqualTo(member);//equals hascode

    }

    @Test
    void findMemberByNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("19990618",formatter);

        Member member = Member.builder()
                .userId("test3")
                .userPwd("test3")
                .userName("test3")
                .nickName("test3")
                .gender("M")
                .email("test3@test3.com")
                .phone("01011112223")
                .birth(java.sql.Date.valueOf(birth))
                .build();
        memberMapper.save(member);
        Member memberByNo = memberMapper.findMemberByNo(member.getUserNo());
        log.info("member:{}",memberByNo);
        assertThat(memberByNo).isNotNull();

    }

    @Test
    void findMember() {//로그인

        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("19970909",formatter);

        Member member = Member.builder()
                .userId("loginTest")
                .userPwd("loginTest")
                .userName("loginTest")
                .nickName("loginTest")
                .gender("M")
                .email("loginTest@loginTest.com")
                .phone("01055556666")
                .birth(java.sql.Date.valueOf(birth))
                .build();
        memberMapper.save(member);

        MemberDto.findDto logUser=new MemberDto.findDto();
        logUser.setUserId("loginTest");
        logUser.setUserPwd("loginTest");

        //when
        Member loginMember = memberMapper.findMember(logUser.getUserId(), logUser.getUserPwd());

        //then
        assertThat(loginMember).isNotNull();


    }

    @Test
    void updateMemberWithPassword() {

        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("20000101",formatter);
        Member member = Member.builder()
                .userId("test2")
                .userPwd("test2")
                .userName("test2")
                .nickName("test2")
                .gender("M")
                .email("test2@test2.com")
                .phone("01012345678")
                .birth(java.sql.Date.valueOf(birth))
                .build();
        memberMapper.save(member);
        MemberDto.updateDto updateDto=new MemberDto.updateDto();
        updateDto.setNickName("updateTest");
        updateDto.setEmail("test2@test2.com");
        updateDto.setPhone("01022221111");
        updateDto.setUserPwd("");

        //when
        memberMapper.updateMember(member.getUserNo(),updateDto);
        Member updatedMember=memberMapper.findMemberByNo(member.getUserNo());

        //then
        String updatedPassword=updatedMember.getUserPwd();
        assertThat(updatedPassword).isEqualTo(member.getUserPwd());//pwd는 변경되면 안된다.
        String updatedNickName =updatedMember.getNickName();
        assertThat(updatedNickName).isEqualTo("updateTest");

    }

    @Test
    void updateMemberWithoutPassword() {

        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("20000101",formatter);
        Member member = Member.builder()
                .userId("test2")
                .userPwd("test2")
                .userName("test2")
                .nickName("test2")
                .gender("M")
                .email("test2@test2.com")
                .phone("01012345678")
                .birth(java.sql.Date.valueOf(birth))
                .build();

        memberMapper.save(member);
        MemberDto.updateDto updateDto=new MemberDto.updateDto();
        updateDto.setNickName("updateTest");
        updateDto.setEmail("test2@test2.com");
        updateDto.setPhone("01022221111");
        updateDto.setUserPwd("updatePassWord");

        //when
        memberMapper.updateMember(member.getUserNo(),updateDto);
        Member updatedMember=memberMapper.findMemberByNo(member.getUserNo());

        //then
        String updatedPassword=updatedMember.getUserPwd();
        assertThat(updatedPassword).isEqualTo("updatePassWord");//pwd는변경
        String updatedNickName =updatedMember.getNickName();
        assertThat(updatedNickName).isEqualTo("updateTest");

    }

    @Test
    void delete() {//회원 탈퇴
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse("20000101",formatter);
        Member member = Member.builder()
                .userId("test2")
                .userPwd("test2")
                .userName("test2")
                .nickName("test2")
                .gender("M")
                .email("test2@test2.com")
                .phone("01012345678")
                .birth(java.sql.Date.valueOf(birth))
                .build();
        memberMapper.save(member);
        memberMapper.deleteMember(member.getUserNo());
        assertThat(memberMapper.findMemberByNo(member.getUserNo())).isEqualTo(null);


    }

}