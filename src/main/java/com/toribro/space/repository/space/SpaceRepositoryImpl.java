package com.toribro.space.repository.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.service.space.SpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SpaceRepositoryImpl implements SpaceRepository {

    private final SpaceMapper spaceMapper;

    @Override
    public void enroll(SpaceDto.EnrollDto enrollDto, SpaceDto.SpaceAttachmentDto attachmentDto) {

    }

    @Override
    public List<SpaceService> spaces() {
        return List.of();
    }
}
