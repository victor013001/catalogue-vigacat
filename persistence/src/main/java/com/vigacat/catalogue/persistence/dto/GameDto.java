package com.vigacat.catalogue.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private Long id;
    private String name;
    private String developer;
    private LocalDate releaseDate;
}
