package com.toribro.space.entity.member;

import com.toribro.space.entity.member.status.HostGuest;
import com.toribro.space.entity.member.status.OuterStatus;
import com.toribro.space.entity.space.Space;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="MEMBER")
//@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {


    @Id
    @Column(name="user_no")
    private int userNo; //번호

    private String userId; //아이디
    private String userPwd; //비번
    private String userName; //이름
    private String nickName; //닉네임
    private String gender; //성별
    private String phone; //휴대폰번호
    private String birth; //생일
    private String email; //이메일
    private LocalDateTime joinDate; //가입일
    private LocalDateTime modifyDate; //회원정보 수정일
    private String userAdmin; //관리자

    @Enumerated(EnumType.STRING)
    private OuterStatus outStatus; // 탈퇴상태

    @Enumerated(EnumType.STRING)
    private HostGuest userHost; // 호스트



    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Space> spaces=new ArrayList<>();

    public void addSpace(Space space){
        this.spaces.add(space);
        space.setMember(this);
    }


}
