package api.soldout.io.soldout.service.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.dtos.entity.OrderDto.OrderStatus;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import java.util.List;

/**
 * .
 */

public interface OrderService {

  void orderNow(OrderCommand command);

  List<OrderDto> findByUserId(int userId);

  List<OrderDto> findByProductId(int productId);

  void updateOrderStatus(int orderId, OrderStatus status);

}
