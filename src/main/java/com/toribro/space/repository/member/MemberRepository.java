package com.toribro.space.repository.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberRepository {

    private final MemberMapper memberMapper;


    public void save(MemberDto.CreateDto createDto) {

         Member member=Member.builder().
                 userId(createDto.getUserId()).
                 userPwd(createDto.getUserPwd()).
                 userName(createDto.getUserName()).
                 nickName(createDto.getNickName()).
                 gender(createDto.getGender()).
                 phone(createDto.getPhone()).
                 birth(createDto.getBirth()).
                 email(createDto.getEmail()).
                 build();
         memberMapper.save(member);
    }


    public Member findMember(MemberDto.findDto findDto) {
        return null;
    }



    public void updateMember(Long id, MemberDto.updateDto updateDto) {

    }



    public void out(Long userNo) {

    }


}
