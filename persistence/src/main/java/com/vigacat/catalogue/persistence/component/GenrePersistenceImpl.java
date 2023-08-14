package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.repository.GenreRepository;
import com.vigacat.catalogue.persistence.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenrePersistenceImpl implements GenrePersistence {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getGenresByNames(List<String> genres) {
        return genreRepository.getGenresByNames(genres).stream()
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .collect(Collectors.toList());
    }
}
