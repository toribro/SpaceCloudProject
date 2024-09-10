package com.toribro.space.repository.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    public Member findMemberById(Long id) {
        return null;
    }

    @Override
    public Member findMember(MemberDto.findDto findDto) {
        return memberMapper.findMember(findDto.getUserId(),findDto.getUserPwd());
    }

    @Override
    public void updateMember(Long userNo, MemberDto.updateDto updateDto) {
        memberMapper.updateMember(userNo, updateDto);
    }

    @Override
    public void out(Long userNo) {

    }
}
