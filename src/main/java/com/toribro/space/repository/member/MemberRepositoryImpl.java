package com.toribro.space.repository.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;


    @Override
    public void save(MemberDto.CreateDto createDto) {

        //날짜형식 변환 (들어오는 STRING형식과 같아야한다)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birth= LocalDate.parse(createDto.getBirth(),formatter);

            Member member=Member.builder().
                    userId(createDto.getUserId()).
                    userPwd(createDto.getUserPwd()).
                    userName(createDto.getUserName()).
                    nickName(createDto.getNickName()).
                    gender(createDto.getGender()).
                    phone(createDto.getPhone()).
                    birth(java.sql.Date.valueOf(birth)).
                    email(createDto.getEmail()).
                    build();
            memberMapper.save(member);



    }

    @Override
    public Member findMember(Long id) {
        return null;
    }
    @Override
    public Member findMember(MemberDto.findDto findDto) {

//        Member member=Member.builder().
//                 userId(findDto.getUserId())
//                .userPwd(findDto.getUserPwd())
//                .build();
//
        return memberMapper.findMember(findDto.getUserId(),findDto.getUserPwd());
    }

    @Override
    public void updateMember(Long id, MemberDto.updateDto updateDto) {

    }


    @Override
    public void out(Long userNo) {

    }


    public void login(MemberDto.findDto findDto) {

    }


}
