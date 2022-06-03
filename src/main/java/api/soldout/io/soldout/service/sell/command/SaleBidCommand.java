package api.soldout.io.soldout.service.sell.command;

import api.soldout.io.soldout.util.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class SaleBidCommand {

  private int userId;
  private int productId;
  private int size;
  private int price;
  private int period;
  private OrderType type;

}
