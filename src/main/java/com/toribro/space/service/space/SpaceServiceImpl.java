package com.toribro.space.service.space;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.repository.space.SpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;

    @Override
    public void enroll(SpaceDto.EnrollDto enrollDto, SpaceDto.SpaceAttachmentDto attachmentDto) {


    }

    @Override
    public List<SpaceService> spaces() {
        return List.of();
    }
}
