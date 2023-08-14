package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.persistence.dto.GameDto;
import com.vigacat.catalogue.persistence.dto.GameToSaveDto;

import java.time.LocalDate;

public interface GamePersistence {
    GameDto getGameById(Long id);

    boolean gameTitleAndReleaseDateExist(String title, LocalDate releaseDate);

    GameDto saveNewGame(GameToSaveDto gameToSaveDto, String usernameAuthenticated);
}
