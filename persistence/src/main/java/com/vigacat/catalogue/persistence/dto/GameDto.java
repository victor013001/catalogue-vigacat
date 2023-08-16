package com.vigacat.catalogue.persistence.dto;

import com.vigacat.catalogue.dao.entity.types.EsrbType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private Long id;
    private String title;
    private LocalDate releaseDate;
    private EsrbType esrb;
    private List<DeveloperDto> developers;
    private List<PublisherDto> publishers;
    private List<GenreDto> genres;
}
