package api.soldout.io.soldout.service.sale;

import api.soldout.io.soldout.domain.SaleDto;
import api.soldout.io.soldout.domain.SaleDto.SaleStatus;
import api.soldout.io.soldout.repository.sale.SaleRepository;
import api.soldout.io.soldout.service.sale.command.SaleBidCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

  private final SaleRepository saleRepository;

  /**
   * 판매 입찰 등록을 위한 엔티티 객체 생성.
   * .@param command
   */
  @Override
  @Transactional
  public void saleBid(SaleBidCommand command) {

    SaleDto saleDto = SaleDto.builder()
        .userId(command.getUserId())
        .productId(command.getProductId())
        .size(command.getSize())
        .price(command.getPrice())
        .date(command.getPeriod())
        .type(command.getType())
        .status(SaleStatus.BID_PROGRESS)
        .build();

    saleRepository.saveSale(saleDto);

  }

  @Override
  @Transactional(readOnly = true)
  public List<SaleDto> findByUserId(int userId) {

    return saleRepository.findByUserId(userId);

  }

  @Override
  @Transactional(readOnly = true)
  public List<SaleDto> findByProductId(int productId) {

    return saleRepository.findByProductId(productId);

  }

  @Override
  @Transactional(readOnly = true)
  public List<SaleDto> findByProductIdAndSizeAndPriceAndSaleStatus(int productId, int size,
      int price, SaleStatus status) {

    return saleRepository.findByProductIdAndSizeAndPriceAndSaleStatus(productId, size, price,
        status);

  }

  @Override
  @Transactional
  public void updateSaleStatus(int saleId, SaleStatus status) {

    saleRepository.updateSaleStatus(saleId, status);

  }

}
