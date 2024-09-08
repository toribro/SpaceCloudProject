package com.toribro.space.domain.dto.space;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
*
* */

public class SpaceDto {

    @Getter
    @Setter
    @ToString
    public static class EnrollDto{

        @NotBlank(message="공간명은 필수 입니다.")
        private String spaceName;

        @NotBlank(message="공간유형은 필수 입니다.")
        private List<SpaceKinds> spaceKinds= Arrays.asList(
                new SpaceKinds("party", "파티룸"),
                new SpaceKinds("cafe", "카페"),
                new SpaceKinds("lecture", "강의실"),
                new SpaceKinds("meeting", "회의실"),
                new SpaceKinds("seminar", "세미나실"),
                new SpaceKinds("study", "스터디룸")
        );

        @NotEmpty(message="1개이상 선택하세요")
        private List<String> spaceKind ;

        @NotBlank(message="공간 소개는 필수 입니다.")
        private String spaceOneIntroduce;

        @NotBlank(message="공간 한줄소개는 필수 입니다.")
        private String spaceIntroduce;

        @NotBlank(message="1개이상 선택하세요")
        private String spaceTags ;

        @NotBlank(message="주소는 필수입니다.")
        private String spaceAddress;

        @NotBlank(message="상세주소는 필수입니다.")
        private String spaceDetailAddress;

        private String spaceLocation;

        @NotBlank(message="전화번호는 필수 입니다.")
        private String spaceTel;

        @NotBlank(message="가격을 입력하세요")
        private String spacePrice;

        @NotBlank(message="수용인원은 필수 입니다.")
        private int spaceCapacity;

//        @NotBlank
//        private int userNo;

    }

    @AllArgsConstructor
    @Getter
    private static class SpaceKinds{
        private String id;
        private String value;
    }

    @Getter
    @Setter
    public static class SpaceAttachmentDto{

        private String originName;
        private String changeName;
        private String filePath;
        private int fileLevel;
    }



}
