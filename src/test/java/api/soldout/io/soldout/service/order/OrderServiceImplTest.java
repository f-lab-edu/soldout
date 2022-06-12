package api.soldout.io.soldout.service.order;

import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.service.trade.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  OrderService orderService;

  @Mock
  OrderRepository orderRepository;

  @Mock
  TradeService tradeService;

  @BeforeEach
  void init() {

    orderService = new OrderServiceImpl(orderRepository, tradeService);

  }

}
