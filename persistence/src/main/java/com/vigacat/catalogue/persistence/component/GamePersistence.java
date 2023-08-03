package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.persistence.dto.GameDto;

public interface GamePersistence {
    GameDto getGameById(Long id);
}
