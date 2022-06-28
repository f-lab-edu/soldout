package api.soldout.io.soldout.listener;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.dtos.entity.OrderDto.OrderStatus;
import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import api.soldout.io.soldout.listener.event.OrderCreated;
import api.soldout.io.soldout.service.order.OrderService;
import api.soldout.io.soldout.service.sale.SaleService;
import api.soldout.io.soldout.service.trade.TradeService;
import api.soldout.io.soldout.util.enums.SaleType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TradeEventListenerTest {

  TradeEventListener tradeEventListener;

  TradeService tradeService;

  SaleService saleService;

  OrderService orderService;

  int userId = 1;
  int productId = 1;
  int orderId = 1;
  int saleId = 1;
  int size = 250;
  int price = 100000;
  int period = 3;

  @BeforeEach
  void init() {

    tradeService = mock(TradeService.class);

    saleService = mock(SaleService.class);

    orderService = mock(OrderService.class);

    tradeEventListener = new TradeEventListener(tradeService, orderService, saleService);

  }

  @Test
  @DisplayName("즉시 구매 요청에 따른 거래 채결 로직 테스트")
  void matchTradeByOrderTest() {
    // given
    OrderCreated event = OrderCreated.from(productId, orderId, size, price);

    SaleDto saleDto = SaleDto.builder()
        .id(saleId)
        .productId(productId)
        .userId(userId)
        .size(size)
        .price(price)
        .date(period)
        .type(SaleType.SALE_BID)
        .status(SaleStatus.BID_PROGRESS)
        .build();

    List<SaleDto> saleDtoList = new ArrayList<>();
    saleDtoList.add(saleDto);

    // when
    when(saleService.findByProductIdAndSizeAndPriceAndSaleStatus(anyInt(), anyInt(), anyInt(),
        any())).thenReturn(saleDtoList);

    tradeEventListener.matchTradeByOrder(event);

    // then
    assertThat(saleService.findByProductIdAndSizeAndPriceAndSaleStatus(productId, size, price,
        SaleStatus.BID_PROGRESS))
        .isEqualTo(saleDtoList);

    verify(tradeService, times(1))
        .saveTrade(productId, orderId, saleDto.getId(), size, price);

    verify(saleService, times(1))
        .updateSaleStatus(saleDto.getId(), SaleStatus.MATCHING_COMPLETE);

    verify(orderService, times(1))
            .updateOrderStatus(event.getOrderId(), OrderStatus.MATCHING_COMPLETE);

  }

}
