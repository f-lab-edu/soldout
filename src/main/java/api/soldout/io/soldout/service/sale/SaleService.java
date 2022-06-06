package api.soldout.io.soldout.service.sale;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.service.sale.command.SaleBidCommand;
import java.util.List;

/**
 * .
 */

public interface SaleService {

  void saleBid(SaleBidCommand command);

  List<SaleDto> findByUserId(int userId);

  List<SaleDto> findByProductId(int productId);

}
