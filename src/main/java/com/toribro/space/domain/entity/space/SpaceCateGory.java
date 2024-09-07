package com.toribro.space.domain.entity.space;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="spacecategory")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class SpaceCateGory {

    @Id
    private int categoryNum;
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="space_no")
    private Space space;


    public void addSpace(Space space) {
        this.space = space;
        space.getSpaceCateGories().add(this);
    }

}
