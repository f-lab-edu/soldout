package api.soldout.io.soldout.listener;

import api.soldout.io.soldout.dtos.entity.OrderDto.OrderStatus;
import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import api.soldout.io.soldout.exception.AlreadyMatchedException;
import api.soldout.io.soldout.listener.event.SaveOrderEvent;
import api.soldout.io.soldout.service.order.OrderService;
import api.soldout.io.soldout.service.sale.SaleService;
import api.soldout.io.soldout.service.trade.TradeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * .
 */

@Component
@RequiredArgsConstructor
public class TradeEventListener {

  private final TradeService tradeService;

  private final OrderService orderService;

  private final SaleService saleService;

  /**
   * saveOrder() 메소드 실행하는 이벤트 발생시 수행될 리스너 메소드
   * 구매 저장 요청과 동일한 구매 제품, 사이즈, 가격이면서 "판매 입찰 중"인 sale 개체를 찾고 매칭시켜주는 역할을 한다.
   * 매칭에 성공할 경우, trade 개체를 생성해 테이블에 저장하고
   * order, sale 개체의 상태를 "거래 완료"로 변경한다.
   */

  @EventListener
  public void matchTradeByOrder(SaveOrderEvent event) {

    List<SaleDto> saleDtoList = saleService.findByProductIdAndSizeAndPriceAndSaleStatus(

        event.getProductId(), event.getSize(), event.getPrice(), SaleStatus.BID_PROGRESS

    );

    if (saleDtoList.size() == 0) {

      throw new AlreadyMatchedException("찾는 판매 입찰가가 없습니다.");

    }

    int saleId = findFirstSaleId(saleDtoList);

    tradeService.saveTrade(

        event.getProductId(), event.getId(), saleId, event.getSize(), event.getPrice()

    );

    orderService.updateOrderStatus(event.getId(), OrderStatus.MATCHING_COMPLETE);

    saleService.updateSaleStatus(saleId, SaleStatus.MATCHING_COMPLETE);

  }

  private int findFirstSaleId(List<SaleDto> saleDtoList) {

    Optional<SaleDto> findSaleDto = saleDtoList.stream().findFirst();

    return findSaleDto.map(SaleDto::getId).orElse(0);

  }

}

