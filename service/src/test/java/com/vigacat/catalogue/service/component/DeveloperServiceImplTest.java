package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.DeveloperPersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceImplTest {

    @InjectMocks
    private DeveloperServiceImpl developerService;

    @Mock
    private DeveloperPersistence developerPersistence;

    @Test
    public void createDeveloperTest() {

        String bungie = "Bungie";

        Mockito.when(developerPersistence.existDeveloper(bungie))
                .thenReturn(false);

        developerService.createDeveloper(bungie);

        Mockito.verify(developerPersistence)
                .createDeveloper(bungie);
    }
}
