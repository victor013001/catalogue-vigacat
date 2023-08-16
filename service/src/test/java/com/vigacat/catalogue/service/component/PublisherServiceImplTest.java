package com.vigacat.catalogue.service.component;

import com.vigacat.catalogue.persistence.component.PublisherPersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PublisherServiceImplTest {

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Mock
    private PublisherPersistence publisherPersistence;

    @Test
    public void getGenreByNames() {

        String bungie = "Bungie";

        Mockito.when(publisherPersistence.existPublisher(bungie))
                .thenReturn(false);

        publisherService.createPublisher(bungie);

        Mockito.verify(publisherPersistence)
                .createPublisher(bungie);
    }
}
