package api.soldout.io.soldout.service.trade;

import api.soldout.io.soldout.domain.TradeDto;
import java.util.List;

/**
 * .
 */

public interface TradeService {

  void saveTrade(int productId, int orderId, int saleId, int size, int price);

  List<TradeDto> findByOrderId(int orderId);

  List<TradeDto> findBySaleId(int saleId);

}
