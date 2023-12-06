package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.entity.Developer;
import com.vigacat.catalogue.dao.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeveloperPersistenceImpl implements DeveloperPersistence {

    private final DeveloperRepository developerRepository;

    @Override
    @Transactional
    public void createDeveloper(String developer) {
        developerRepository.save(buildDeveloper(developer));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existDeveloper(String developer) {
        return developerRepository.existsByDeveloperIgnoreCase(developer);
    }

    private Developer buildDeveloper(String developer) {
        return Developer.builder()
                .developer(developer)
                .build();
    }
}
