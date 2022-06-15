package api.soldout.io.soldout.service.trade;

import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.util.enums.SaleType;
import java.util.List;

/**
 * .
 */

public interface TradeService {

  void saveTrade(int productId, int orderId, int saleId, int size, int price);

  void matchTradeByOrder(int orderId, int productId, int size, int price);

  List<TradeDto> findByOrderId(int orderId);

  List<TradeDto> findBySaleId(int saleId);

}
