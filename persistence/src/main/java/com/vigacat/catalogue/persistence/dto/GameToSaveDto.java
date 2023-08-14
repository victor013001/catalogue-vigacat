package com.vigacat.catalogue.persistence.dto;

import com.vigacat.catalogue.dao.entity.types.EsrbType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class GameToSaveDto {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Release date is mandatory")
    private LocalDate releaseDate;

    @NotNull(message = "ESRB is mandatory")
    private EsrbType esrb;

    @NotEmpty(message = "One or more developers are required")
    private List<String> developers;

    @NotEmpty(message = "One or more publishers are required")
    private List<String> publishers;

    @NotEmpty(message = "One or more genres are required")
    private List<String> genres;
}
