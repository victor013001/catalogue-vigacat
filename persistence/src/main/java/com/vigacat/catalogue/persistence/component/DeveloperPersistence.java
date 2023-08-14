package com.vigacat.catalogue.persistence.component;

public interface DeveloperPersistence {

    void createDeveloper(String developer);

    boolean existDeveloper(String developer);
}
