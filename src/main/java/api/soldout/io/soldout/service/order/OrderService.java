package api.soldout.io.soldout.service.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.service.order.command.OrderCommand;

/**
 * .
 */

public interface OrderService {

  void order(OrderCommand command);

  OrderDto findByOrderId(String orderId);

  OrderDto findByUserId(String userId);

  OrderDto findByProductId(String productId);

}
