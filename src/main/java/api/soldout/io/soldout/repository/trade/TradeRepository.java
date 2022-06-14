package api.soldout.io.soldout.repository.trade;

import api.soldout.io.soldout.dtos.entity.TradeDto;
import java.util.List;

/**
 * .
 */

public interface TradeRepository {

  void saveTrade(TradeDto tradeDto);

  List<TradeDto> findByOrderId(int orderId);

  List<TradeDto> findBySaleId(int saleId);

}
