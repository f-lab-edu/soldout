package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.dtos.OrderDto;

/**
 * .
 */

public interface OrderRepository {

  void order(OrderDto order);

}
