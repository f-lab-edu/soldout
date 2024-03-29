package api.soldout.io.soldout.service.order.command;

import api.soldout.io.soldout.domain.OrderDto.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCommand {

  private int userId;
  private int productId;
  private int size;
  private int price;
  private int period;
  private OrderType type;

}
