package com.toribro.space.entity.space;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class SpaceOption {

    @Id
    private int optionNo;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="space_no")
    private Space space;

    public void addSpace(Space space) {
        this.space = space;
        space.getSpaceOptions().add(this);

    }

}
