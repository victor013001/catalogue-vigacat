package com.vigacat.catalogue.persistence.component;

public interface PublisherPersistence {

    void createPublisher(String publisher);

    boolean existPublisher(String publisher);
}
