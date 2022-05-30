package api.soldout.io.soldout.service.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import java.util.List;

/**
 * .
 */

public interface OrderService {

  void order(OrderCommand command);

  List<OrderDto> findByOrderId(String orderId);

  List<OrderDto> findByUserId(String userId);

  List<OrderDto> findByProductId(String productId);

}
