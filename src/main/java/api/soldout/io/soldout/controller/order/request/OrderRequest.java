package api.soldout.io.soldout.controller.order.request;

import api.soldout.io.soldout.dtos.enums.OrderEndDay;
import api.soldout.io.soldout.dtos.enums.OrderType;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * .
 * {
 *   userId : 23
 *   productId : 11
 *   size : 255
 *   price : 129000
 *   type : 즉시 구매 or 구매 입찰
 *   endDay : 30
 * }
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
  private OrderEndDay endDay;

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
        request.getEndDay());
  }
}
