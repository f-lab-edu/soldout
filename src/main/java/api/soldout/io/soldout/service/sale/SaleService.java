package api.soldout.io.soldout.service.sale;

import api.soldout.io.soldout.domain.SaleDto;
import api.soldout.io.soldout.domain.SaleDto.SaleStatus;
import api.soldout.io.soldout.service.sale.command.SaleBidCommand;
import java.util.List;

public interface SaleService {

  void saleBid(SaleBidCommand command);

  List<SaleDto> findByUserId(int userId);

  List<SaleDto> findByProductId(int productId);

  List<SaleDto> findByProductIdAndSizeAndPriceAndSaleStatus(int productId, int size, int price,
      SaleStatus status);

  void updateSaleStatus(int saleId, SaleStatus status);

}
