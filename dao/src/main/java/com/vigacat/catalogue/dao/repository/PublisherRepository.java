package com.vigacat.catalogue.dao.repository;

import com.vigacat.catalogue.dao.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher getReferenceByPublisher(String publisher);

    boolean existsByPublisherIgnoreCase(String publisher);
}
