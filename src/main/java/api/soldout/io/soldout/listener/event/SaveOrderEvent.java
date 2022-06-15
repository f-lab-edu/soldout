package api.soldout.io.soldout.listener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * .
 */

@Getter
public class SaveOrderEvent extends ApplicationEvent {

  private int id;
  private int productId;
  private int size;
  private int price;

  /**
   * .
   */

  public SaveOrderEvent(Object source) {

    super(source);

  }

  /**
   * .
   */

  public SaveOrderEvent(Object source, int id, int productId, int size, int price) {

    super(source);
    this.id = id;
    this.productId = productId;
    this.size = size;
    this.price = price;

  }

  /**
   * .
   */

  public static SaveOrderEvent from(Object source, int id, int productId, int size, int price) {

    return new SaveOrderEvent(source, id, productId, size, price);

  }

}
