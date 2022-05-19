package api.soldout.io.soldout.config;

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
@MapperScan(value = " api.soldout.io.soldout.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class MyBatisConfig {

  @Value("${mybatis.mapper-locations}")
  String mapperPath;

  @Bean(name = "dataSource")
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  /**
   * .
   */

  @Bean(name = "SqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource,
                                             ApplicationContext applicationContext)
                                             throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperPath));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "SessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("SqlSessionFactory")
                                               SqlSessionFactory firstSqlSessionFactory) {
    return new SqlSessionTemplate(firstSqlSessionFactory);
  }

}