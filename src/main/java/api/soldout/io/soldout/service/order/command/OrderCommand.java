package api.soldout.io.soldout.service.order.command;

import api.soldout.io.soldout.dtos.enums.OrderExpirationPeriod;
import api.soldout.io.soldout.dtos.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class OrderCommand {

  private int userId;
  private int productId;
  private int size;
  private int price;
  private OrderType type;
  private OrderExpirationPeriod period;

}
