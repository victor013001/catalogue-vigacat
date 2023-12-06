package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.GenrePersistence;
import com.vigacat.catalogue.persistence.dto.GenreDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceImplTest {

    @InjectMocks
    private GenreServiceImpl genreService;

    @Mock
    private GenrePersistence genrePersistence;

    @Test
    public void getGenreByNames() {

        String action = "Action";
        String shooter = "Shooter";

        List<String> destinyGenreNames = List.of(action, shooter);

        GenreDto actionGenre = GenreDto.builder()
                .genre(action)
                .build();
        GenreDto shooterGenre = GenreDto.builder()
                .genre(shooter)
                .build();

        List<GenreDto> destinyGenres = List.of(actionGenre, shooterGenre);

        Mockito.when(genrePersistence.getGenresByNames(destinyGenreNames))
                .thenReturn(destinyGenres);

        List<GenreDto> destinyGenresResponse = genreService.getGenreByNames(destinyGenreNames);

        Mockito.verify(genrePersistence)
                .getGenresByNames(destinyGenreNames);

        Assertions.assertThat(destinyGenresResponse)
                .extracting(GenreDto::getGenre)
                .contains(action, shooter);
    }
}
