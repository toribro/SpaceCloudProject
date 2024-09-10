package com.toribro.space.domain.entity.vo.space;

import com.toribro.space.domain.entity.common.Attachment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class SpaceDetail {


    private int spaceNo;
    private Long userNo;
    private String spaceName;
    private String spaceKind;
    private String spaceOneIntroduce;
    private String spaceIntroduce;
    private String spaceTag;
    private String spaceInformation;
    private String spaceCaution;
    private String spaceMImg;//공간 메인 이미지
    private String spaceImg;//공간이미지
    private String spaceAddress;
    private String spaceDetailAddress;
    private int spacePrice;
    private String spaceLocation;
    private String spaceTel;
    private int spaceCapacity;
    private List<Attachment> attachments= new ArrayList<>();




}
