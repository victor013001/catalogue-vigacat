package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.dao.entity.types.EsrbType;
import com.vigacat.catalogue.persistence.component.GamePersistence;
import com.vigacat.catalogue.persistence.dto.*;
import com.vigacat.catalogue.service.component.security.util.VigacatSecurityContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GamePersistence gamePersistence;
    @Mock
    private GenreService genreService;
    @Mock
    private DeveloperService developerService;
    @Mock
    private PublisherService publisherService;
    @Mock
    private VigacatSecurityContext vigacatSecurityContext;

    @Test
    public void getGameByIdTest() {

        Long destinyId = 1L;
        String destiny = "Destiny";
        String bungie = "Bungie";
        LocalDate releaseDateDestiny = LocalDate.of(2017, 9, 6);
        EsrbType matureEsrb = EsrbType.M;

        String action = "Action";

        GenreDto actionGenre = GenreDto.builder()
                .genre(action)
                .build();
        List<GenreDto> destinyGenres = List.of(actionGenre);

        DeveloperDto bungieDeveloper = DeveloperDto.builder()
                .developer(bungie)
                .build();
        List<DeveloperDto> destinyDevelopers = List.of(bungieDeveloper);

        PublisherDto bungiePublisher = PublisherDto.builder()
                .publisher(bungie)
                .build();
        List<PublisherDto> destinyPublishers = List.of(bungiePublisher);


        GameDto destinyGameDto = GameDto.builder()
                .id(destinyId)
                .title(destiny)
                .esrb(matureEsrb)
                .releaseDate(releaseDateDestiny)
                .genres(destinyGenres)
                .developers(destinyDevelopers)
                .publishers(destinyPublishers)
                .build();

        Mockito.when(gamePersistence.getGameById(destinyId))
                .thenReturn(destinyGameDto);

        final GameDto gameResponse = gameService.getGameById(destinyId);

        Mockito.verify(gamePersistence)
                .getGameById(destinyId);

        Assertions.assertThat(gameResponse)
                .hasFieldOrPropertyWithValue("id", destinyId)
                .hasFieldOrPropertyWithValue("title", destiny)
                .hasFieldOrPropertyWithValue("releaseDate", releaseDateDestiny)
                .hasFieldOrPropertyWithValue("esrb", matureEsrb);

        Assertions.assertThat(gameResponse.getGenres())
                .extracting(GenreDto::getGenre)
                .contains(action);

        Assertions.assertThat(gameResponse.getPublishers())
                .extracting(PublisherDto::getPublisher)
                .contains(bungie);

        Assertions.assertThat(gameResponse.getDevelopers())
                .extracting(DeveloperDto::getDeveloper)
                .contains(bungie);
    }

    @Test
    public void createNewGameTest() {

        String usernameAdminAuthenticated = "Admin";

        String destiny = "Destiny";
        String bungie = "Bungie";
        LocalDate releaseDateDestiny = LocalDate.of(2017, 9, 6);
        EsrbType matureEsrb = EsrbType.M;

        String action = "Action";
        List<String> destinyGenreNames = List.of(action);

        GenreDto actionGenre = GenreDto.builder()
                .genre(action)
                .build();
        List<GenreDto> destinyGenres = List.of(actionGenre);

        DeveloperDto bungieDeveloper = DeveloperDto.builder()
                .developer(bungie)
                .build();
        List<DeveloperDto> destinyDevelopers = List.of(bungieDeveloper);

        PublisherDto bungiePublisher = PublisherDto.builder()
                .publisher(bungie)
                .build();
        List<PublisherDto> destinyPublishers = List.of(bungiePublisher);

        List<String> destinyDeveloperNames = List.of(bungie);
        List<String> destinyPublisherNames = List.of(bungie);

        GameToSaveDto gameDestinyToSaveDto = GameToSaveDto.builder()
                .title(destiny)
                .releaseDate(releaseDateDestiny)
                .genres(destinyGenreNames)
                .developers(destinyDeveloperNames)
                .publishers(destinyPublisherNames)
                .esrb(matureEsrb)
                .build();

        GameDto destinyGameDto = GameDto.builder()
                .id(1L)
                .title(destiny)
                .esrb(matureEsrb)
                .releaseDate(releaseDateDestiny)
                .genres(destinyGenres)
                .publishers(destinyPublishers)
                .developers(destinyDevelopers)
                .build();

        Mockito.when(gamePersistence.gameTitleAndReleaseDateExist(destiny, releaseDateDestiny))
                .thenReturn(false);

        Mockito.when(genreService.getGenreByNames(destinyGenreNames))
                .thenReturn(destinyGenres);

        Mockito.when(vigacatSecurityContext.getUsernameAuthenticated())
                .thenReturn(usernameAdminAuthenticated);

        Mockito.when(gamePersistence.saveNewGame(gameDestinyToSaveDto, usernameAdminAuthenticated))
                .thenReturn(destinyGameDto);

        GameDto gameCreated = gameService.createNewGame(gameDestinyToSaveDto);

        Mockito.verify(gamePersistence)
                .gameTitleAndReleaseDateExist(destiny, releaseDateDestiny);

        Mockito.verify(genreService)
                .getGenreByNames(destinyGenreNames);

        Mockito.verify(developerService)
                .createDeveloper(bungie);

        Mockito.verify(publisherService)
                .createPublisher(bungie);


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
