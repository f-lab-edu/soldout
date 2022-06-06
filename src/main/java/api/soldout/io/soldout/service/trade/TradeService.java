package api.soldout.io.soldout.service.trade;

/**
 * .
 */

public interface TradeService {

  void signTradeByOrder(int productId, int orderId, int size, int price);

}
