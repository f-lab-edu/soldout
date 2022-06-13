package api.soldout.io.soldout.service.order;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.repository.order.OrderRepositoryImpl;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import api.soldout.io.soldout.service.trade.TradeService;
import api.soldout.io.soldout.service.trade.TradeServiceImpl;
import api.soldout.io.soldout.util.enums.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  OrderService orderService;

  OrderRepository orderRepository;

  TradeService tradeService;

  @BeforeEach
  void init() {

    orderRepository = mock(OrderRepositoryImpl.class);

    tradeService = mock(TradeServiceImpl.class);

    orderService = new OrderServiceImpl(orderRepository, tradeService);

  }

  @Test
  @DisplayName("즉시 구매 테스트")
  void orderNowTest() {
    // given
    OrderCommand command = new OrderCommand(
        1, 1, 250, 100000, 3, OrderType.ORDER_NOW
    );

    // when
    orderService.orderNow(command);

    // then
    verify(orderRepository)
        .saveOrder(any());
    verify(orderRepository, times(1))
        .saveOrder(any());

    verify(tradeService)
        .signTradeByOrder(anyInt(), anyInt(), anyInt(), anyInt());
    verify(tradeService, times(1))
        .signTradeByOrder(anyInt(), anyInt(), anyInt(), anyInt());

    verify(orderRepository)
        .updateOrderStatus(anyInt(), any());
    verify(orderRepository, times(1))
        .updateOrderStatus(anyInt(), any());

  }

}