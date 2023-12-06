package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.entity.Developer;
import com.vigacat.catalogue.dao.entity.Game;
import com.vigacat.catalogue.dao.entity.Genre;
import com.vigacat.catalogue.dao.entity.Publisher;
import com.vigacat.catalogue.dao.entity.types.EsrbType;
import com.vigacat.catalogue.dao.repository.DeveloperRepository;
import com.vigacat.catalogue.dao.repository.GameRepository;
import com.vigacat.catalogue.dao.repository.GenreRepository;
import com.vigacat.catalogue.dao.repository.PublisherRepository;
import com.vigacat.catalogue.persistence.dto.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class GamePersistenceImplTest {

    @InjectMocks
    private GamePersistenceImpl gamePersistence;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private DeveloperRepository developerRepository;
    @Mock
    private PublisherRepository publisherRepository;
    @Mock
    private GenreRepository genreRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    public void getGameByIdTest() {

        String titleDestiny = "Destiny";
        LocalDate releaseDateDestiny = LocalDate.of(2017, 9, 6);
        Long idDestiny = 1L;

        Game gameDestiny = Game.builder()
                .id(idDestiny)
                .title(titleDestiny)
                .releaseDate(releaseDateDestiny)
                .build();

        Mockito.when(gameRepository.findById(idDestiny))
                .thenReturn(Optional.of(gameDestiny));

        GameDto gameResponse = gamePersistence.getGameById(idDestiny);

        Mockito.verify(gameRepository)
                .findById(idDestiny);

        Assertions.assertThat(gameResponse)
                .hasFieldOrPropertyWithValue("title", titleDestiny)
                .hasFieldOrPropertyWithValue("releaseDate", releaseDateDestiny);
    }

    @Test
    public void gameTitleAndReleaseDateExistTest() {

        String titleDestiny = "Destiny";
        LocalDate releaseDateDestiny = LocalDate.of(2017, 9, 6);

        Mockito.when(gameRepository.existsGameByTitleIgnoreCaseAndReleaseDate(titleDestiny, releaseDateDestiny))
                .thenReturn(true);

        boolean destinyGameExist = gamePersistence.gameTitleAndReleaseDateExist(titleDestiny, releaseDateDestiny);

        Mockito.verify(gameRepository)
                .existsGameByTitleIgnoreCaseAndReleaseDate(titleDestiny, releaseDateDestiny);

        Assertions.assertThat(destinyGameExist)
                .isTrue();
    }

    @Test
    public void saveNewGameTest() {

        String usernameAdminAuthenticated = "Admin";

        String destiny = "Destiny";
        LocalDate releaseDateDestiny = LocalDate.of(2017, 9, 6);

        String action = "Action";
        List<String> destinyGenres = List.of(action);

        String bungie = "Bungie";
        List<String> destinyDevelopers = List.of(bungie);

        List<String> destinyPublishers = List.of(bungie);

        GameToSaveDto gameDestinyToSaveDto = GameToSaveDto.builder()
                .title(destiny)
                .releaseDate(releaseDateDestiny)
                .genres(destinyGenres)
                .developers(destinyDevelopers)
                .publishers(destinyPublishers)
                .esrb(EsrbType.M)
                .build();

        Genre actionGenre = Genre.builder()
                .id(1L)
                .genre(action)
                .build();

        Developer bungieDeveloper = Developer.builder()
                .id(1L)
                .developer(bungie)
                .build();

        Publisher bungiePublisher = Publisher.builder()
                .id(1L)
                .publisher(bungie)
                .build();

        Mockito.when(genreRepository.getReferenceByGenre(action))
                .thenReturn(actionGenre);

        Mockito.when(publisherRepository.getReferenceByPublisher(bungie))
                .thenReturn(bungiePublisher);

        Mockito.when(developerRepository.getReferenceByDeveloper(bungie))
                .thenReturn(bungieDeveloper);

        Mockito.when(gameRepository.save(Mockito.any(Game.class)))
                .then((Answer<Game>) invocationOnMock -> {
                    Game destinyGameSaved = invocationOnMock.getArgument(0);
                    destinyGameSaved.setId(1L);
                    return destinyGameSaved;
                });

        GameDto gameCreated = gamePersistence.saveNewGame(gameDestinyToSaveDto, usernameAdminAuthenticated);

        Mockito.verify(genreRepository)
                .getReferenceByGenre(action);

        Mockito.verify(publisherRepository)
                .getReferenceByPublisher(bungie);

        Mockito.verify(developerRepository)
                .getReferenceByDeveloper(bungie);

        Mockito.verify(gameRepository)
                .save(Mockito.any(Game.class));

        Assertions.assertThat(gameCreated)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("title", destiny)
                .hasFieldOrPropertyWithValue("releaseDate", releaseDateDestiny)
                .hasFieldOrPropertyWithValue("esrb", EsrbType.M);

        Assertions.assertThat(gameCreated.getGenres())
                .extracting(GenreDto::getGenre)
                .contains(action);

        Assertions.assertThat(gameCreated.getPublishers())
                .extracting(PublisherDto::getPublisher)
                .contains(bungie);

        Assertions.assertThat(gameCreated.getDevelopers())
                .extracting(DeveloperDto::getDeveloper)
                .contains(bungie);
    }
}
