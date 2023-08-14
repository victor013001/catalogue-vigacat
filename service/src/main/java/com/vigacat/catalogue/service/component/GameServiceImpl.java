package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.GamePersistence;
import com.vigacat.catalogue.persistence.dto.GameDto;
import com.vigacat.catalogue.persistence.dto.GameToSaveDto;
import com.vigacat.catalogue.service.component.security.util.VigacatSecurityContext;
import com.vigacat.catalogue.service.exceptions.GameCreateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private static final String LOG_PREFIX = "Game Service >>";

    private final GamePersistence gamePersistence;
    private final GenreService genreService;
    private final DeveloperService developerService;
    private final PublisherService publisherService;
    private final VigacatSecurityContext vigacatSecurityContext;

    @Override
    public GameDto getGameById(Long id) {
        log.info("{} Get game by id {}", LOG_PREFIX, id);
        return gamePersistence.getGameById(id);
    }

    @Override
    public GameDto createNewGame(GameToSaveDto gameToSaveDto) {
        checkGameUnique(gameToSaveDto.getTitle(), gameToSaveDto.getReleaseDate());
        checkGenresExists(gameToSaveDto.getGenres());
        createDevelopersIfNeeded(gameToSaveDto.getDevelopers());
        createPublisherIfNeeded(gameToSaveDto.getPublishers());
        String usernameAuthenticated = vigacatSecurityContext.getUsernameAuthenticated();
        log.info("{} Save game {}. Created by {}", LOG_PREFIX, gameToSaveDto, usernameAuthenticated);
        return gamePersistence.saveNewGame(gameToSaveDto, usernameAuthenticated);
    }

    private void createPublisherIfNeeded(List<String> publishers) {
        publishers
                .forEach(publisherService::createPublisher);
    }

    private void createDevelopersIfNeeded(List<String> developers) {
        developers
                .forEach(developerService::createDeveloper);
    }

    private void checkGenresExists(List<String> genreNames) {
        if (genreService.getGenreByNames(genreNames).size() != genreNames.size()) {
            throw new GameCreateException(genreNames.toString(), GameCreateException.Type.NON_EXISTENT_GENRES);
        }
    }

    private void checkGameUnique(String title, LocalDate releaseDate) {
        if (gamePersistence.gameTitleAndReleaseDateExist(title, releaseDate)) {
            throw new GameCreateException(title, releaseDate.toString(), GameCreateException.Type.DUPLICATE_GAME);
        }
    }

}
