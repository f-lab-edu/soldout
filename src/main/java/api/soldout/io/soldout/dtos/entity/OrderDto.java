package api.soldout.io.soldout.dtos.entity;

import api.soldout.io.soldout.dtos.enums.OrderExpirationPeriod;
import api.soldout.io.soldout.dtos.enums.OrderType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

/**
 * .
 */

@Getter
public class OrderDto {

  private int id;
  private int userId;
  private int productId;
  private int size;
  private int price;
  private OrderType type;
  private OrderExpirationPeriod period;
  private LocalDateTime day;

  /**
   * .
   */

  @Builder
  public OrderDto(int id, int userId, int productId, int size, int price,
      OrderType type, OrderExpirationPeriod period) {
    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.size = size;
    this.price = price;
    this.type = type;
    this.period = period;

  }

  /**
   * .
   */

  public void calcExpirationDay(OrderExpirationPeriod period) {

    this.day = LocalDateTime.now().plusDays(period.getDays());

  }

}
