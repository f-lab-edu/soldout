package api.soldout.io.soldout.service.trade;

import static api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus.DELIVER_FROM_SALE_COMPLETE;
import static api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus.TRADE_SIGNING;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.service.sale.SaleService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    // 찾은 saleId가 이미 다른 사용자에 의해 거래 성사가 되었다면 trade 테이블에 해당 saleId가 있어야 한다.



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
    // id가 기본적으로 인덱스로 사용되고 자동 증가로 입력되기 때문에 그 순서로 loop를 돈다.
    // 생성 시기가 빠른 녀석을 찾기 위한 로직이 굳이 필요할지?
    for (SaleDto tempSaleDto : saleDtoList) {

      if (tempSaleDto.getSize() == size && tempSaleDto.getPrice() == price) {

        return tempSaleDto.getId();

      }

    }

    throw new RuntimeException("판매 입찰 기간이 지나서 갑작스럽게 매칭 가능한 판매자가 사라진 경우");

  }

}
