package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import java.util.List;

/**
 * .
 */

public interface OrderRepository {

  void order(OrderDto order);

  List<OrderDto> findByOrderId(String orderId);

  List<OrderDto> findByUserId(String userId);

  List<OrderDto> findByProductId(String productId);

}
