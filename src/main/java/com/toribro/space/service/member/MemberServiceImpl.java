package com.toribro.space.service.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;
import com.toribro.space.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void  save(MemberDto.CreateDto createDto) {
        memberRepository.save(createDto);
    }

    @Override
    public Member findMemberByNo(Long userNo) {
        return memberRepository.findMemberByNo(userNo);
    }
    @Override
    public Member findMember(MemberDto.findDto findDto) {//로그인 처리
        return memberRepository.findMember(findDto);
    }

    @Override
    @Transactional
    public void updateMember(Long userNo, MemberDto.updateDto updateDto) {
        memberRepository.updateMember(userNo, updateDto);
    }

    @Override
    @Transactional
    public void deleteMember(Long userNo) {
        memberRepository.deleteMember(userNo);
    }



}
