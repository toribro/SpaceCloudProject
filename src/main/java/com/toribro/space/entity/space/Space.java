package com.toribro.space.entity.space;

import com.toribro.space.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="SPACE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Space {

 ///@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="space_no")
    private int spaceNo;

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
    private LocalDateTime spaceEnrollDate;
    private int spaceCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus spaceReservationStatus;

    @Enumerated(EnumType.STRING)
    private RegisterStatus spaceEnrollStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_no")
    private Member member;

    @OneToMany(mappedBy="space",fetch = FetchType.LAZY )
    private List<SpaceCateGory> spaceCateGories= new ArrayList<>();


    @OneToMany(mappedBy="space",fetch = FetchType.LAZY )
    private List<SpaceOption> spaceOptions= new ArrayList<>();


    //편의 메소드
    public void addMember(Member member){
        this.member=member;
        member.getSpaces().add(this);

    }
    public void addSpaceCategory(SpaceCateGory spaceCateGory){
          this.spaceCateGories.add(spaceCateGory);
          spaceCateGory.setSpace(this);


    }


}
