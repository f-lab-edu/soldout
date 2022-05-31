package api.soldout.io.soldout.controller.order.request;

import api.soldout.io.soldout.service.order.command.OrderCommand;
import api.soldout.io.soldout.util.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

  private int userId;
  private int productId;
  private int size;
  private int price;
  private int period;
  private OrderType type;

  /**
   * .
   */

  public static OrderCommand toCommand(OrderRequest request) {

    return new OrderCommand(
        request.getUserId(),
        request.getProductId(),
        request.getSize(),
        request.getPrice(),
        request.getPeriod(),
        request.getType());
  }
}
