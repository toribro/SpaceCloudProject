package com.toribro.space.domain.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

public class MemberDto {

    @Getter
    @Setter
    @ToString
    public static class CreateDto {

        @NotBlank(message="이름은 필수 입니다.")
        private String userName;

        @NotBlank(message = "아이디는 필수 입력 사항입니다.")
        @Pattern(message = "잘못된 아이디 형식입니다. (영문자와 숫자만 사용 가능, 6자 이상 20자 이하)", regexp = "^[a-zA-Z0-9]{6,20}$")
        private String userId;

        @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
        @Pattern(message = "비밀번호는 8자 이상 20자 이하, 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.",
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")
        private String userPwd;

        @NotBlank(message = "비밀번호확인은 필수 입력 사항입니다.")
        private String userPwdCheck;

        @NotBlank(message = "닉네임은 필수 입력 사항입니다.")
        @Pattern(message = "닉네임은 특수 문자를 제외하고 최대 8자까지 가능합니다.", regexp = "^[a-zA-Z0-9가-힣]{1,8}$")
        private String nickName;

        @NotBlank(message = "생년월일은 필수 입력 사항입니다.")
        @Pattern(message = "생년월일 형식이 잘못되었습니다. (예: YYYYMMDD)", regexp = "^\\d{8}$")
        private String birth;

        @NotBlank(message = "성별은 필수 입력 사항입니다.")
        @Pattern(message = "성별은 'M' 또는 'F'로 입력해야 합니다.", regexp = "^[MF]$")
        private String gender;

        @NotBlank(message = "이메일은 필수 입력 사항입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "전화번호는 필수 입력 사항입니다.")
        @Pattern(message = "전화번호 형식이 잘못되었습니다. (예: 01012345678)", regexp = "^01[016789]\\d{7,8}$")
        private String phone;
    }

    @Getter
    @Setter
    @ToString
    public static class findDto{

        @NotBlank(message="아이디를 입력하세요")
        private String userId;
        @NotBlank(message="비밀번호를 입력하세요")
        private String userPwd;

    }

    @Getter
    @Setter
    @ToString
    public static class updateDto{

        private String userName;
        private String userId;
        private String userPwd;
        private String userPwdCheck;
        private String nickName;
        private String birth;
        private String gender;
        private String email;
        private String phone;
    }

}
