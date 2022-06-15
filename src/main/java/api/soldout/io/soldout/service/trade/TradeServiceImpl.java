package api.soldout.io.soldout.service.trade;

import api.soldout.io.soldout.dtos.entity.OrderDto.OrderStatus;
import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus;
import api.soldout.io.soldout.exception.AlreadyMatchedException;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.repository.sale.SaleRepository;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

  private final TradeRepository tradeRepository;

  private final SaleRepository saleRepository;

  private final OrderRepository orderRepository;

  @Override
  public void saveTrade(int productId, int orderId, int saleId, int size, int price) {

    TradeDto tradeDto = TradeDto.builder()
        .productId(productId)
        .orderId(orderId)
        .saleId(saleId)
        .size(size)
        .price(price)
        .status(TradeStatus.MATCHING_COMPLETE)
        .date(LocalDateTime.now())
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

  /**
   * saveOrder() 메소드 실행시, 리스너에 의해 실행되는 메소드.
   * 구매 저장 요청과 동일한 구매 제품, 사이즈, 가격이면서 "판매 입찰 중"인 sale 개체를 찾고 매칭시켜주는 역할을 한다.
   * 매칭에 성공할 경우, trade 개체를 생성해 테이블에 저장하고
   * order, sale 개체의 상태를 "거래 완료"로 변경한다.
   */

  @Override
  public void matchTradeByOrder(int orderId, int productId, int size, int price) {

    List<SaleDto> saleDtoList = saleRepository.findByProductIdAndSizeAndPriceAndSaleStatus(

        productId, size, price, SaleStatus.BID_PROGRESS

    );

    if (saleDtoList.size() == 0) {

      throw new AlreadyMatchedException("찾는 판매 입찰가가 없습니다.");

    }

    int saleId = findFirstSaleId(saleDtoList);

    saveTrade(productId, orderId, saleId, size, price);

    orderRepository.updateOrderStatus(orderId, OrderStatus.MATCHING_COMPLETE);

    saleRepository.updateSaleStatus(saleId, SaleStatus.MATCHING_COMPLETE);

  }

  private int findFirstSaleId(List<SaleDto> saleDtoList) {

    Optional<SaleDto> findSaleDto = saleDtoList.stream().findFirst();

    return findSaleDto.map(SaleDto::getId).orElse(0);

  }

}
