package com.vigacat.catalogue.dao.repository;

import com.vigacat.catalogue.dao.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(value = "SELECT DISTINCT g " +
            "FROM Genre g " +
            "WHERE g.genre IN :genres")
    List<Genre> getGenresByNames(@Param("genres") List<String> genres);

    Genre getReferenceByGenre(String genre);
}
