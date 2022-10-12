package api.soldout.io.soldout.service.sale.command;

import api.soldout.io.soldout.domain.SaleDto.SaleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleBidCommand {

  private int userId;
  private int productId;
  private int size;
  private int price;
  private int period;
  private SaleType type;

}
