package api.soldout.io.soldout.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * .
 */

@Configuration
@MapperScan(value = " api.soldout.io.soldout.mapper",
            sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

  @Value("${mybatis.mapper-locations}")
  String mapperPath;

  /**
   * .
   */

  @Bean(name = "slaveDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
  public DataSource slaveDataSource() {

    return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .build();
  }

  /**
   * .
   */

  @Bean(name = "slaveSqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource,
      ApplicationContext applicationContext)
      throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperPath));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "slaveSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("slaveSqlSessionFactory")
      SqlSessionFactory firstSqlSessionFactory) {
    return new SqlSessionTemplate(firstSqlSessionFactory);
  }

}
