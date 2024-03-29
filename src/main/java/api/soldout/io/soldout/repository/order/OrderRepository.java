package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.domain.OrderDto;
import api.soldout.io.soldout.domain.OrderDto.OrderStatus;
import java.util.List;

public interface OrderRepository {

  void saveOrder(OrderDto order);

  List<OrderDto> findByUserId(int userId);

  List<OrderDto> findByProductId(int productId);

  void updateOrderStatus(int orderId, OrderStatus status);

}
