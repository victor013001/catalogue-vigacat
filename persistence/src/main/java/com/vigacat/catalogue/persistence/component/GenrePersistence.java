package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.persistence.dto.GenreDto;

import java.util.List;

public interface GenrePersistence {

    List<GenreDto> getGenresByNames(List<String> genres);
}
