package api.soldout.io.soldout.dtos.entity;

import api.soldout.io.soldout.util.enums.OrderType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
public class OrderDto {

  private int id;
  private int userId;
  private int productId;
  private int size;
  private int price;
  private OrderType type;
  private LocalDateTime day;

  /**
   * .
   */

  @Builder
  public OrderDto(int id, int userId, int productId, int size, int price, int day, OrderType type) {

    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.size = size;
    this.price = price;
    this.type = type;
    this.day = LocalDateTime.now().plusDays(day);

  }

}
