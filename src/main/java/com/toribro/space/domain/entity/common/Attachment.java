package com.toribro.space.domain.entity.common;

import com.toribro.space.domain.entity.space.Space;
import com.toribro.space.domain.entity.status.FileStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Attachment {

    @Id
    private int fileNo;
    private String originName;
    private String changeName;
    private String filePath;
    private Date uploadDate;
    private int fileLevel;

    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="space_no")
    private Space space;

    public void addSpace(Space space) {
        this.space = space;
    }

}
