package com.toribro.space.service.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;

public interface MemberService {
    void save(MemberDto.CreateDto createDto);
    Member findMemberByNo(Long userNo);
    Member findMember(MemberDto.findDto findDto);//로그인 처리
    void updateMember(Long userNo,MemberDto.updateDto updateDto);
    void deleteMember(Long userNo);

}
