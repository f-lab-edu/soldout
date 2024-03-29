package api.soldout.io.soldout.config;

import api.soldout.io.soldout.enums.DataSourceType;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSourceConfig {

  static class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

      return TransactionSynchronizationManager
          .isCurrentTransactionReadOnly() ? DataSourceType.SLAVE : DataSourceType.MASTER;

    }

  }

  @Bean(name = "routingDataSource")
  public DataSource routingDataSource(
            @Qualifier("masterDataSource") final DataSource masterDataSource,
            @Qualifier("slaveDataSource") final DataSource slaveDataSource) {

    ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

    Map<Object, Object> dataSourceMap = new HashMap<>();

    dataSourceMap.put(DataSourceType.MASTER, masterDataSource);
    dataSourceMap.put(DataSourceType.SLAVE, slaveDataSource);

    routingDataSource.setTargetDataSources(dataSourceMap);
    routingDataSource.setDefaultTargetDataSource(masterDataSource);

    return routingDataSource;

  }

  @Bean(name = "dataSource")
  public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {

    return new LazyConnectionDataSourceProxy(routingDataSource);

  }

}
