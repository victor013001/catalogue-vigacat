package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.dto.GameDto;
import com.vigacat.catalogue.persistence.dto.GameToSaveDto;

public interface GameService {
    GameDto getGameById(Long id);

    GameDto createNewGame(GameToSaveDto gameToSaveDto);
}
