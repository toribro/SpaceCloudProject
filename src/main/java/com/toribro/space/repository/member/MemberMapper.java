package com.toribro.space.repository.member;

import com.toribro.space.domain.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void save(Member member);
    Member findById(@Param("id")Long id, @Param("password")Long passWord);
    void update(Member member);
    void delete(Long userNo);
}
