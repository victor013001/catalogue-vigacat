package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.entity.Game;
import com.vigacat.catalogue.dao.repository.DeveloperRepository;
import com.vigacat.catalogue.dao.repository.GameRepository;
import com.vigacat.catalogue.dao.repository.GenreRepository;
import com.vigacat.catalogue.dao.repository.PublisherRepository;
import com.vigacat.catalogue.persistence.dto.GameDto;
import com.vigacat.catalogue.persistence.dto.GameToSaveDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GamePersistenceImpl implements GamePersistence {

    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public GameDto getGameById(Long id) {
        return gameRepository.findById(id)
                .map(game -> modelMapper.map(game, GameDto.class))
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean gameTitleAndReleaseDateExist(String title, LocalDate releaseDate) {
        return gameRepository.existsGameByTitleIgnoreCaseAndReleaseDate(title, releaseDate);
    }

    @Override
    @Transactional
    public GameDto saveNewGame(GameToSaveDto gameToSaveDto, String usernameAuthenticated) {
        Game gameToSave = createGameToSave(gameToSaveDto, usernameAuthenticated);
        return modelMapper.map(gameRepository.save(gameToSave), GameDto.class);
    }

    private Game createGameToSave(GameToSaveDto gameToSaveDto, String usernameAuthenticated) {
        Game gameToSave = modelMapper.map(gameToSaveDto, Game.class);
        setDeveloperReferences(gameToSave, gameToSaveDto.getDevelopers());
        setPublisherReferences(gameToSave, gameToSaveDto.getPublishers());
        setGenreReferences(gameToSave, gameToSaveDto.getGenres());
        gameToSave.setCreatedAt(LocalDateTime.now());
        gameToSave.setCreatedBy(usernameAuthenticated);
        return gameToSave;
    }

    private void setGenreReferences(Game gameToSave, List<String> genres) {
        gameToSave.setGenres(genres.stream()
                .map(genreRepository::getReferenceByGenre)
                .collect(Collectors.toList())
        );
    }

    private void setPublisherReferences(Game gameToSave, List<String> publishers) {
        gameToSave.setPublishers(publishers.stream()
                .map(publisherRepository::getReferenceByPublisher)
                .collect(Collectors.toList())
        );
    }

    private void setDeveloperReferences(Game gameToSave, List<String> developers) {
        gameToSave.setDevelopers(developers.stream()
                .map(developerRepository::getReferenceByDeveloper)
                .collect(Collectors.toList())
        );
    }
}
