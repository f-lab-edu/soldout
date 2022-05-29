package api.soldout.io.soldout.controller.order.request;

import api.soldout.io.soldout.dtos.enums.OrderExpirationPeriod;
import api.soldout.io.soldout.dtos.enums.OrderType;
import api.soldout.io.soldout.service.order.command.OrderCommand;
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
  private OrderType type;
  private OrderExpirationPeriod period;

  /**
   * .
   */

  public static OrderCommand toCommand(OrderRequest request) {

    return new OrderCommand(
        request.getUserId(),
        request.getProductId(),
        request.getSize(),
        request.getPrice(),
        request.getType(),
        request.getPeriod());
  }
}
