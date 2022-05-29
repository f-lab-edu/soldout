package api.soldout.io.soldout.dtos.entity;

import api.soldout.io.soldout.dtos.enums.OrderEndDay;
import api.soldout.io.soldout.dtos.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * .
 */

@Getter
@Builder
@AllArgsConstructor
public class OrderDto {

  private int id;
  private int userId;
  private int productId;
  private int size;
  private int price;
  private OrderType type;
  private OrderEndDay endDay;

}
