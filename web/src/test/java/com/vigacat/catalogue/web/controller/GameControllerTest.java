package com.vigacat.catalogue.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vigacat.catalogue.dao.entity.types.EsrbType;
import com.vigacat.catalogue.persistence.dto.*;
import com.vigacat.catalogue.service.component.GameService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameService gameService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void getGameTest() throws Exception {

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
                .id(1L)
                .title(destiny)
                .esrb(matureEsrb)
                .releaseDate(releaseDateDestiny)
                .genres(destinyGenres)
                .publishers(destinyPublishers)
                .developers(destinyDevelopers)
                .build();

        Mockito.when(gameService.getGameById(destinyId))
                .thenReturn(destinyGameDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/game")
                        .param("id", String.valueOf(destinyId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(destinyId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(Matchers.is(destiny)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.esrb").value(Matchers.is("M")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.esrb").value(Matchers.is("M")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate[0]").value(Matchers.is(releaseDateDestiny.getYear())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate[1]").value(Matchers.is(releaseDateDestiny.getMonthValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate[2]").value(Matchers.is(releaseDateDestiny.getDayOfMonth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[0].genre").value(Matchers.is(action)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishers[0].publisher").value(Matchers.is(bungie)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.developers[0].developer").value(Matchers.is(bungie)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createNewGameTest() throws Exception {

        Long destinyId = 1L;
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
        String gameDestinyToSaveDtoJson = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(gameDestinyToSaveDto);

        GameDto destinyGameDto = GameDto.builder()
                .id(destinyId)
                .title(destiny)
                .esrb(matureEsrb)
                .releaseDate(releaseDateDestiny)
                .genres(destinyGenres)
                .publishers(destinyPublishers)
                .developers(destinyDevelopers)
                .build();

        Mockito.when(gameService.createNewGame(gameDestinyToSaveDto))
                .thenReturn(destinyGameDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/game")
                        .content(gameDestinyToSaveDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(destinyId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(Matchers.is(destiny)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.esrb").value(Matchers.is("M")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate[0]").value(Matchers.is(releaseDateDestiny.getYear())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate[1]").value(Matchers.is(releaseDateDestiny.getMonthValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate[2]").value(Matchers.is(releaseDateDestiny.getDayOfMonth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[0].genre").value(Matchers.is(action)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishers[0].publisher").value(Matchers.is(bungie)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.developers[0].developer").value(Matchers.is(bungie)))
                .andDo(MockMvcResultHandlers.print());


    }


}
