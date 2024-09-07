package com.toribro.space.service.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;

public interface MemberService {
    void save(MemberDto.CreateDto createDto);
    Member findMember(MemberDto.findDto findDto);
    void updateMember(Member member,MemberDto.updateDto updateDto);
    void out(Long userNo);
}
