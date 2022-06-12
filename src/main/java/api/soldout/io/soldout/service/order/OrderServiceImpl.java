package api.soldout.io.soldout.service.order;


import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import api.soldout.io.soldout.service.trade.TradeService;
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
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final TradeService tradeService;

  @Override
  public void orderNow(OrderCommand command) {

    OrderDto order = OrderDto.builder()
        .userId(command.getUserId())
        .productId(command.getProductId())
        .size(command.getSize())
        .price(command.getPrice())
        .type(command.getType())
        .day(command.getPeriod())
        .build();

    orderRepository.saveOrder(order);

    tradeService.signTradeByOrder(
        order.getProductId(), order.getId(), order.getSize(), order.getPrice()
    );

  }

  @Override
  public List<OrderDto> findByUserId(String userId) {

    return orderRepository.findByUserId(userId);

  }

  @Override
  public List<OrderDto> findByProductId(String productId) {

    return orderRepository.findByProductId(productId);

  }

}
