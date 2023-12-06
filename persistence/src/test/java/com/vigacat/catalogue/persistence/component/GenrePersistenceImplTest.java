package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.entity.Genre;
import com.vigacat.catalogue.dao.repository.GenreRepository;
import com.vigacat.catalogue.persistence.dto.GenreDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GenrePersistenceImplTest {

    @InjectMocks
    private GenrePersistenceImpl genrePersistence;

    @Mock
    private GenreRepository genreRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    public void getGenresByNamesTest() {

        String action = "Action";
        String survival = "Survival";

        List<String> genreNames = List.of(action, survival);

        Genre actionGenre = Genre.builder()
                .genre(action)
                .id(1L)
                .build();

        Genre survivalGenre = Genre.builder()
                .id(2L)
                .genre(survival)
                .build();

        List<Genre> genres = List.of(actionGenre, survivalGenre);

        Mockito.when(genreRepository.getGenresByNames(genreNames))
                .thenReturn(genres);

        List<GenreDto> genreDtosResponse = genrePersistence.getGenresByNames(genreNames);

        Mockito.verify(genreRepository)
                .getGenresByNames(genreNames);

        Assertions.assertThat(genreDtosResponse)
                .extracting(GenreDto::getGenre)
                .contains(action, survival);
    }
}
