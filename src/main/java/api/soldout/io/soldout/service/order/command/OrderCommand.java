package api.soldout.io.soldout.service.order.command;

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
  private String type;
  private int endDay;

}
