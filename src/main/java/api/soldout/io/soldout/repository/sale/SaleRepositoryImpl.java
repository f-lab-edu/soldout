package api.soldout.io.soldout.repository.sale;

import api.soldout.io.soldout.domain.SaleDto;
import api.soldout.io.soldout.domain.SaleDto.SaleStatus;
import api.soldout.io.soldout.mapper.SaleMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class SaleRepositoryImpl implements SaleRepository {

  private final SaleMapper saleMapper;

  @Override
  public void saveSale(SaleDto saleDto) {

    saleMapper.insertSale(saleDto);

  }

  @Override
  public List<SaleDto> findByUserId(int userId) {

    return saleMapper.findByUserId(userId);

  }

  @Override
  public List<SaleDto> findByProductIdAndSizeAndPriceAndSaleStatus(int productId, int size,
      int price, SaleStatus status) {

    return saleMapper.findByProductIdAndSizeAndPriceAndSaleStatus(productId, size, price, status);

  }

  @Override
  public List<SaleDto> findByProductId(int productId) {

    return saleMapper.findByProductId(productId);

  }

  @Override
  public void updateSaleStatus(int saleId, SaleStatus status) {

    saleMapper.updateSaleStatus(saleId, status);

  }

}
