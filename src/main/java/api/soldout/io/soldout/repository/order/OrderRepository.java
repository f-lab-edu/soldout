package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;

/**
 * .
 */

public interface OrderRepository {

  void order(OrderDto order);

}
