package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.GamePersistence;
import com.vigacat.catalogue.persistence.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GamePersistence gamePersistence;

    @Override
    public GameDto getGameById(Long id) {
        return gamePersistence.getGameById(id);
    }
}
