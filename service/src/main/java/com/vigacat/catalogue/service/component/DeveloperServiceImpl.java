package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.DeveloperPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private static final String LOG_PREFIX = "Developer Service >>";

    private final DeveloperPersistence developerPersistence;

    @Override
    public void createDeveloper(String developer) {
        if (!developerPersistence.existDeveloper(developer)) {
            log.info("{} Create developer {}", LOG_PREFIX, developer);
            developerPersistence.createDeveloper(developer);
        }
    }

}
