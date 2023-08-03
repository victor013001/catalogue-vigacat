package com.vigacat.catalogue.web;

import com.vigacat.catalogue.dao.config.VigacatCatalogueDaoConfig;
import com.vigacat.catalogue.persistence.configuration.VigacatCataloguePersistenceConfig;
import com.vigacat.catalogue.service.config.VigacatCatalogueServiceConfig;
import com.vigacat.catalogue.web.config.VigacatCatalogueWebConfig;
import com.vigacat.security.client.filter.configuration.ClientVigacatFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        VigacatCatalogueDaoConfig.class,
        VigacatCataloguePersistenceConfig.class,
        VigacatCatalogueServiceConfig.class,
        VigacatCatalogueWebConfig.class,
        ClientVigacatFilterConfig.class
})
public class VigacatApplication {
    public static void main(String... args) {
        SpringApplication.run(VigacatApplication.class, args);
    }
}
