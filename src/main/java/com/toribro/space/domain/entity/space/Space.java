package com.toribro.space.domain.entity.space;

import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="SPACE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Space {

 ///@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="space_no")
    private int spaceNo;

    @Column(unique=true,nullable=false)
    private String spaceName;

    @Column(nullable=false)
    private String spaceKind;

    @Column(nullable=false)
    private String spaceOneIntroduce;

    @Column(nullable=false)
    private String spaceIntroduce;

    private String spaceTag;

    @Column(nullable=false)
    private String spaceInformation;
    private String spaceCaution;
    private String spaceMImg;//공간 메인 이미지
    private String spaceImg;//공간이미지
    @Column(nullable=false)
    private String spaceAddress;
    @Column(nullable=false)
    private String spaceDetailAddress;
    private int spacePrice;
    @Column(nullable=false)
    private String spaceLocation;
    private String spaceTel;
    private int spaceCapacity;
    private LocalDateTime spaceEnrollDate;

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

    @OneToMany(mappedBy = "space",fetch = FetchType.LAZY )
    private List<Attachment> attachments= new ArrayList<>();


    //편의 메소드
    public void addMember(Member member){
        this.member=member;
        member.getSpaces().add(this);

    }
    public void addSpaceCategory(SpaceCateGory spaceCateGory){
          this.spaceCateGories.add(spaceCateGory);
          spaceCateGory.setSpace(this);


    }
    public void addAttachment(Attachment attachment){
       this.attachments.add(attachment);
       attachment.setSpace(this);
    }


}
