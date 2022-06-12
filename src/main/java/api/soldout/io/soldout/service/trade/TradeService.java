package api.soldout.io.soldout.service.trade;

import api.soldout.io.soldout.dtos.entity.TradeDto;
import java.util.List;

/**
 * .
 */

public interface TradeService {

  void signTradeByOrder(int productId, int orderId, int size, int price);

  List<TradeDto> findByOrderId(int orderId);

  List<TradeDto> findBySaleId(int saleId);

}
