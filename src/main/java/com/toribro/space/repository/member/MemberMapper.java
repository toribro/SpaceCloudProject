package com.toribro.space.repository.member;

import com.toribro.space.domain.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void save(Member member);
    Member findById(@Param("id")Long userNo);
    void update(Member member);
    void delete(Long userNo);
    Member findMember(@Param("id")String id, @Param("password")String passWord);
}
