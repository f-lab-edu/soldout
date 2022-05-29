package api.soldout.io.soldout.service.order;

import api.soldout.io.soldout.service.order.command.OrderCommand;

/**
 * .
 */

public interface OrderService {

  void order(OrderCommand command);

}
