package com.vigacat.catalogue.dao.repository;

import com.vigacat.catalogue.dao.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Developer getReferenceByDeveloper(String developer);

    boolean existsByDeveloperIgnoreCase(String developer);
}
