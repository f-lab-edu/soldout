package api.soldout.io.soldout.repository.sale;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import java.util.List;

/**
 * .
 */

public interface SaleRepository {

  void saveSale(SaleDto saleDto);

  List<SaleDto> findByUserId(int userId);

  List<SaleDto> findByProductId(int productId);

  void updateSaleStatus(int saleId, SaleStatus status);

}
