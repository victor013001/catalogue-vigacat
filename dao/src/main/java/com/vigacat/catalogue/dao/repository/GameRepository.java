package com.vigacat.catalogue.dao.repository;

import com.vigacat.catalogue.dao.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository  extends JpaRepository<Game,Long> {
    Optional<Game> findById(Long id);
}
