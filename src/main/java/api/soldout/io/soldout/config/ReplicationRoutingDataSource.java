package api.soldout.io.soldout.config;

import api.soldout.io.soldout.util.enums.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * .
 */

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {

    return TransactionSynchronizationManager
        .isCurrentTransactionReadOnly() ? DataSourceType.SLAVE : DataSourceType.MASTER;

  }

}
