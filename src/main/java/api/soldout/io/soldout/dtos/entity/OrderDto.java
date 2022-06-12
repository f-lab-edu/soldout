package api.soldout.io.soldout.dtos.entity;

import api.soldout.io.soldout.util.enums.OrderType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
public class OrderDto {

  /**
   * .
   */

  @Getter
  @AllArgsConstructor
  public enum OrderStatus {

    ORDER_PROGRESS("입찰 진행중"),
    ORDER_SIGNED("거래 완료");

    private final String text;

  }

  private int id;
  private int userId;
  private int productId;
  private int size;
  private int price;
  private LocalDateTime day;
  private OrderType type;
  private OrderStatus status;

  /**
   * .
   */

  @Builder
  public OrderDto(int id, int userId, int productId, int size, int price, int day,
                  OrderType type, OrderStatus status) {

    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.size = size;
    this.price = price;
    this.type = type;
    this.day = LocalDateTime.now().plusDays(day);
    this.status = status;

  }

}
