package api.soldout.io.soldout.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

  @Primary
  @Bean(name = "masterDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.master.hikari")
  public DataSource masterDataSource() {

    return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .build();
  }

  @Bean(name = "slaveDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
  public DataSource slaveDataSource() {

    return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .build();
  }

}
