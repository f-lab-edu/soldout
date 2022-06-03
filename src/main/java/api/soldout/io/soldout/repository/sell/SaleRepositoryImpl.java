package api.soldout.io.soldout.repository.sell;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.mapper.SaleMapper;
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

  private SaleMapper saleMapper;

  @Override
  public void saveSale(SaleDto saleDto) {

    saleMapper.insertSale(saleDto);

  }

}
