package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.dto.GameDto;

public interface GameService {
    GameDto getGameById(Long id);
}
