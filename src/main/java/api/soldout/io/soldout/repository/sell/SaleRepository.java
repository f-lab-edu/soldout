package api.soldout.io.soldout.repository.sell;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import java.util.List;

/**
 * .
 */

public interface SaleRepository {

  void saveSale(SaleDto saleDto);

  List<SaleDto> findByUserId(int userId);

  List<SaleDto> findByProductId(int productId);

}
