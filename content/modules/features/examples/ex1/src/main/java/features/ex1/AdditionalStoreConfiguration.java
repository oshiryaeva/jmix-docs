package features.ex1;

import io.jmix.core.JmixModules;
import io.jmix.core.Resources;
import io.jmix.data.impl.JmixEntityManagerFactoryBean;
import io.jmix.data.impl.JmixTransactionManager;
import io.jmix.data.persistence.DbmsSpecifics;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class AdditionalStoreConfiguration {

    @Bean
    @ConfigurationProperties("additional.datasource")
    DataSourceProperties additionalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "additional.datasource.hikari")
    DataSource additionalDataSource(@Qualifier("additionalDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean additionalEntityManagerFactory(
            @Qualifier("additionalDataSource") DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter,
            DbmsSpecifics dbmsSpecifics,
            JmixModules jmixModules,
            Resources resources) {
        return new JmixEntityManagerFactoryBean("additional", dataSource, jpaVendorAdapter, dbmsSpecifics, jmixModules, resources);
    }

    @Bean
    JpaTransactionManager additionalTransactionManager(@Qualifier("additionalEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JmixTransactionManager("additional", entityManagerFactory);
    }
}
