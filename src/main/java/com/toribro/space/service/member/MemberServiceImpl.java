package com.toribro.space.service.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;
import com.toribro.space.repository.member.MemberRepository;
import com.toribro.space.repository.member.MemberRepositoryImpl;
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
    public void save(MemberDto.CreateDto createDto) {
        memberRepository.save(createDto);
    }

    @Override
    public Member findMember(Long id) {
        return null;
    }
    @Override
    public Member findMember(MemberDto.findDto findDto) {
        return memberRepository.findMember(findDto);
    }


    @Override
    public void updateMember(Member member,MemberDto.updateDto updateDto) {

    }


    @Override
    public void out(Long userNo) {

    }


}
