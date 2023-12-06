package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.entity.Developer;
import com.vigacat.catalogue.dao.repository.DeveloperRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperPersistenceImplTest {

    @InjectMocks
    private DeveloperPersistenceImpl developerPersistence;

    @Mock
    private DeveloperRepository developerRepository;

    @Captor
    ArgumentCaptor<Developer> developerArgumentCaptor;


    @Test
    public void createDeveloperTest() {

        String bungieDeveloper = "Bungie";

        developerPersistence.createDeveloper(bungieDeveloper);

        Mockito.verify(developerRepository)
                .save(developerArgumentCaptor.capture());

        Developer developerCaptorValue = developerArgumentCaptor.getValue();

        Assertions.assertThat(developerCaptorValue)
                .hasFieldOrPropertyWithValue("developer", bungieDeveloper);

    }

    @Test
    public void existDeveloperTest() {

        String bungieDeveloper = "Bungie";

        Mockito.when(developerRepository.existsByDeveloperIgnoreCase(bungieDeveloper))
                .thenReturn(true);

        boolean bungieDeveloperExist = developerPersistence.existDeveloper(bungieDeveloper);

        Mockito.verify(developerRepository)
                .existsByDeveloperIgnoreCase(bungieDeveloper);

        Assertions.assertThat(bungieDeveloperExist)
                .isTrue();

    }

}
