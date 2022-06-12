package api.soldout.io.soldout.service.trade;

import static api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus.SALE_PROGRESS;
import static api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus.TRADE_SIGNING;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.exception.AlreadyMatchedException;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.service.sale.SaleService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

  private final SaleService saleService;

  private final TradeRepository tradeRepository;

  @Override
  public void signTradeByOrder(int productId, int orderId, int size, int price) {

    List<SaleDto> saleDtoList = saleService.findByProductId(productId);

    int saleId = findMatchedSaleDto(saleDtoList, size, price);

    TradeDto tradeDto = TradeDto.builder()
        .productId(productId)
        .orderId(orderId)
        .saleId(saleId)
        .size(size)
        .price(price)
        .status(TRADE_SIGNING)
        .day(LocalDateTime.now())
        .build();

    tradeRepository.saveTrade(tradeDto);

    // saleDto 의 상태를 변경 (입찰 -> 판매 완료)
    // updateSaleStatus(saleId);

  }

  @Override
  public List<TradeDto> findByOrderId(int orderId) {

    return tradeRepository.findByOrderId(orderId);

  }

  @Override
  public List<TradeDto> findBySaleId(int saleId) {

    return tradeRepository.findBySaleId(saleId);

  }

  private int findMatchedSaleDto(List<SaleDto> saleDtoList, int size, int price) {

    // id가 기본적으로 인덱스로 사용되고 자동 증가로 입력되기 때문에 그 순서로 루프를 수행
    // 판매 상태가 상태 판매 입찰중이면서 가격과 사이즈가 같은 녀석을 찾도록!!!!!!
    for (SaleDto tempSaleDto : saleDtoList) {

      if (tempSaleDto.getStatus().equals(SALE_PROGRESS)
          && (tempSaleDto.getSize() == size)
          && (tempSaleDto.getPrice() == price)) {

        return tempSaleDto.getId();

      }

    }

    return 0;

  }

}
