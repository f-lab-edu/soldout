package api.soldout.io.soldout.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value = " api.soldout.io.soldout.mapper",
            sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterMyBatisConfig {

  @Value("${mybatis.mapper-locations}")
  String mapperPath;

  @Primary
  @Bean(name = "masterSqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource,
      ApplicationContext applicationContext) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperPath));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "masterSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("masterSqlSessionFactory")
      SqlSessionFactory firstSqlSessionFactory) {
    return new SqlSessionTemplate(firstSqlSessionFactory);
  }

}
