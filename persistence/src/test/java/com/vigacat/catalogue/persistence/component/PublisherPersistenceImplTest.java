package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.entity.Publisher;
import com.vigacat.catalogue.dao.repository.PublisherRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PublisherPersistenceImplTest {

    @InjectMocks
    private PublisherPersistenceImpl publisherPersistence;

    @Mock
    private PublisherRepository publisherRepository;

    @Captor
    private ArgumentCaptor<Publisher> publisherArgumentCaptor;

    @Test
    public void createPublisherTest() {

        String bungie = "Bungie";

        publisherPersistence.createPublisher(bungie);

        Mockito.verify(publisherRepository)
                .save(publisherArgumentCaptor.capture());

        Publisher publisherCaptured = publisherArgumentCaptor.getValue();

        Assertions.assertThat(publisherCaptured)
                .hasFieldOrPropertyWithValue("publisher", bungie);

    }

    @Test
    public void existPublisherTest() {

        String bungie = "Bungie";

        Mockito.when(publisherRepository.existsByPublisherIgnoreCase(bungie))
                .thenReturn(true);

        boolean publisherBungieExist = publisherPersistence.existPublisher(bungie);

        Assertions.assertThat(publisherBungieExist)
                .isTrue();
    }
}
