package api.soldout.io.soldout.listener.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreated {

  private int orderId;
  private int productId;
  private int size;
  private int price;

  public static OrderCreated from(int orderId, int productId, int size, int price) {

    return new OrderCreated(orderId, productId, size, price);

  }

}
