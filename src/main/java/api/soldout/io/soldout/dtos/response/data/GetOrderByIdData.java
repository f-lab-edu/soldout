package api.soldout.io.soldout.dtos.response.data;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class GetOrderByIdData {

  private List<OrderDto> orderList;

  /**
   * .
   */

  public static GetOrderByIdData from(List<OrderDto> orderList) {

    return new GetOrderByIdData(orderList);

  }

}
