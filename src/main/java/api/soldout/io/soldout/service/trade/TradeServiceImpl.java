package api.soldout.io.soldout.service.trade;

import static api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus.TRADE_SIGNING;

import api.soldout.io.soldout.dtos.entity.SaleDto;
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

    // 찾은 saleId로 trade 테이블을 조회한 결과가 한개라도 있다면? 이미 매칭된 saleId 라는 의미
    if (findBySaleId(saleId).size() > 0) {

      throw new AlreadyMatchedException("이미 다른 구매 희망자와 매칭되었습니다. 다시 즉시 구매를 진행해주세요.");

    }

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

    // id가 기본적으로 인덱스로 사용되고 자동 증가로 입력되기 때문에 그 순서로 루프를 수행 -> 생성 시기가 빠른 녀석을 찾기 위한 로직이 굳이 필요할지?
    for (SaleDto tempSaleDto : saleDtoList) {

      if (tempSaleDto.getSize() == size && tempSaleDto.getPrice() == price) {

        return tempSaleDto.getId();

      }

    }

    // 그래도 찾고자 하는 saleId가 없다면?
    // 어떠한 이유로 구매 버튼을 누른 시점에 존재했던 SaleId가 없어진 경우
    throw new RuntimeException("판매 입찰 기간이 지나서 갑작스럽게 매칭 가능한 판매자가 사라진 경우");

  }

}
