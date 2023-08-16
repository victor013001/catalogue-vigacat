package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.entity.Publisher;
import com.vigacat.catalogue.dao.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PublisherPersistenceImpl implements PublisherPersistence {

    private final PublisherRepository publisherRepository;

    @Override
    @Transactional
    public void createPublisher(String publisher) {
        publisherRepository.save(buildPublisher(publisher));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existPublisher(String publisher) {
        return publisherRepository.existsByPublisherIgnoreCase(publisher);
    }

    private Publisher buildPublisher(String publisher) {
        return Publisher.builder()
                .publisher(publisher)
                .build();
    }
}
