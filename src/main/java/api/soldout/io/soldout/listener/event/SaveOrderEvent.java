package api.soldout.io.soldout.listener.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class SaveOrderEvent {

  private int id;
  private int productId;
  private int size;
  private int price;

  /**
   * .
   */

  public static SaveOrderEvent from(int id, int productId, int size, int price) {

    return new SaveOrderEvent(id, productId, size, price);

  }

}
