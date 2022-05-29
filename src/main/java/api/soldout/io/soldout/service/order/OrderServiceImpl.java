package api.soldout.io.soldout.service.order;


import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.service.order.command.OrderCommand;
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

  @Override
  public void order(OrderCommand command) {

    OrderDto order = OrderDto.builder()
        .userId(command.getUserId())
        .productId(command.getProductId())
        .size(command.getSize())
        .price(command.getPrice())
        .type(command.getType())
        .period(command.getPeriod())
        .build();

    order.calcExpirationDay(order.getPeriod());

    orderRepository.order(order);

  }

  @Override
  public OrderDto findByOrderId(String orderId) {

    return orderRepository.findByOrderId(orderId);

  }

  @Override
  public OrderDto findByUserId(String userId) {

    return orderRepository.findByUserId(userId);

  }

  @Override
  public OrderDto findByProductId(String productId) {

    return orderRepository.findByProductId(productId);

  }
}
