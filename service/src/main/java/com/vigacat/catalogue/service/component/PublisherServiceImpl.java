package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.PublisherPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private static final String LOG_PREFIX = "Publisher Service >>";

    private final PublisherPersistence publisherPersistence;

    @Override
    public void createPublisher(String publisher) {
        if (!publisherPersistence.existPublisher(publisher)) {
            log.info("{} Crete publisher {}", LOG_PREFIX, publisher);
            publisherPersistence.createPublisher(publisher);
        }
    }
}
