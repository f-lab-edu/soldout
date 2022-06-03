package api.soldout.io.soldout.repository.sell;

import api.soldout.io.soldout.dtos.entity.SaleDto;

/**
 * .
 */

public interface SaleRepository {

  void saveSale(SaleDto saleDto);
}
