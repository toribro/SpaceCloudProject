package com.toribro.space.repository.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void save(Member member);//무조건 void
    Member findByNo(@Param("userNo")Long userNo);
    void updateMember(@Param("userNo") Long userNo, @Param("update") MemberDto.updateDto updateDto);
    void delete(Long userNo);
    Member findMember(@Param("id")String userId, @Param("password")String passWord);
}
