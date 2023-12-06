package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.GenrePersistence;
import com.vigacat.catalogue.persistence.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private static final String LOG_PREFIX = "Genre Service >>";

    private final GenrePersistence genrePersistence;

    @Override
    public List<GenreDto> getGenreByNames(List<String> genres) {
        log.info("{} Get genres with names {}", LOG_PREFIX, genres.toString());
        return genrePersistence.getGenresByNames(genres);
    }
}
