package api.soldout.io.soldout.controller.sale.request;

import api.soldout.io.soldout.service.sell.command.SaleBidCommand;
import api.soldout.io.soldout.util.enums.SaleType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleBidRequest {

  @NotNull
  private int size;

  @NotNull
  private int price;

  @Min(value = 3, message = "최소 입찰 기간보다 작습니다.")
  @Max(value = 60, message = "최대 입찰 기간보다 깁니다.")
  private int period;

  @NotNull
  private SaleType type;

  /**
   * .
   */

  public static SaleBidCommand toCommand(SaleBidRequest request, int userId, int productId) {

    return new SaleBidCommand(
        userId,
        productId,
        request.getSize(),
        request.getPrice(),
        request.getPeriod(),
        request.getType());
  }

}
