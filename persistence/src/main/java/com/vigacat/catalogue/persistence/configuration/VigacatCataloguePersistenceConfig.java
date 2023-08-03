package com.vigacat.catalogue.persistence.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.vigacat.catalogue.persistence.component"})
@Import(ModelMapperConfiguration.class)
public class VigacatCataloguePersistenceConfig {
}
