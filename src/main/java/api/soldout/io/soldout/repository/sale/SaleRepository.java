package api.soldout.io.soldout.repository.sale;

import api.soldout.io.soldout.domain.SaleDto;
import api.soldout.io.soldout.domain.SaleDto.SaleStatus;
import java.util.List;

/**
 * .
 */

public interface SaleRepository {

  void saveSale(SaleDto saleDto);

  List<SaleDto> findByUserId(int userId);

  List<SaleDto> findByProductId(int productId);

  List<SaleDto> findByProductIdAndSizeAndPriceAndSaleStatus(int productId, int size, int price,
      SaleStatus status);

  void updateSaleStatus(int saleId, SaleStatus status);

}
