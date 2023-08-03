package com.vigacat.catalogue.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.vigacat.catalogue.dao.entity"})
@EnableJpaRepositories(basePackages = {"com.vigacat.catalogue.dao.repository"})
public class VigacatCatalogueDaoConfig {
}
