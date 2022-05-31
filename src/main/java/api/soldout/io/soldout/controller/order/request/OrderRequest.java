package api.soldout.io.soldout.controller.order.request;

import api.soldout.io.soldout.service.order.command.OrderCommand;
import api.soldout.io.soldout.util.enums.OrderType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

  @NotBlank
  private int userId;

  @NotBlank
  private int productId;

  @NotBlank
  private int size;

  @NotBlank
  private int price;

  @Min(value = 3, message = "최소 입찰 기간보다 작습니다.")
  @Max(value = 60, message = "최대 입찰 기간보다 깁니다.")
  private int period;

  @NotBlank
  private OrderType type;

  /**
   * .
   */

  public static OrderCommand toCommand(OrderRequest request) {

    return new OrderCommand(
        request.getUserId(),
        request.getProductId(),
        request.getSize(),
        request.getPrice(),
        request.getPeriod(),
        request.getType());
  }
}
