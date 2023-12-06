package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getGenreByNames(List<String> genres);
}
