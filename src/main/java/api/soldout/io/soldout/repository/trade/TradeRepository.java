package api.soldout.io.soldout.repository.trade;

import api.soldout.io.soldout.dtos.entity.TradeDto;

/**
 * .
 */

public interface TradeRepository {

  void saveTrade(TradeDto tradeDto);

}
