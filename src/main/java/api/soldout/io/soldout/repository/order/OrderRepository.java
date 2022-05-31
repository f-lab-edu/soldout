package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import java.util.List;

/**
 * .
 */

public interface OrderRepository {

  void saveOrder(OrderDto order);

  List<OrderDto> findByUserId(String userId);

  List<OrderDto> findByProductId(String productId);

}
