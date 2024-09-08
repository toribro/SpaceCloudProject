package com.toribro.space.config;

import com.toribro.space.repository.member.MemberMapper;
import com.toribro.space.repository.member.MemberRepository;
import com.toribro.space.repository.member.MemberRepositoryImpl;
import com.toribro.space.service.member.MemberService;
import com.toribro.space.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
    private final MemberMapper memberMapper;

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(memberMapper);
    }
}
