package com.toribro.space.repository.member;


import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;

public interface MemberRepository {
    public void save(MemberDto.CreateDto createDto);
    public Member findMember(Long id);
    public Member findMember(MemberDto.findDto findDto);
    public void updateMember(Long id, MemberDto.updateDto updateDto);
    void out(Long userNo);
    public void login(MemberDto.findDto findDto);


}
