package api.soldout.io.soldout.service.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.dtos.entity.OrderDto.OrderStatus;
import api.soldout.io.soldout.listener.event.SaveOrderEvent;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  public void orderNow(OrderCommand command) {

    OrderDto order = OrderDto.builder()
        .userId(command.getUserId())
        .productId(command.getProductId())
        .size(command.getSize())
        .price(command.getPrice())
        .date(command.getPeriod())
        .type(command.getType())
        .status(OrderStatus.BID_PROGRESS)
        .build();

    orderRepository.saveOrder(order);

    eventPublisher.publishEvent(

        SaveOrderEvent.from(order.getId(), order.getProductId(), order.getSize(), order.getPrice()

        )

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

  @Override
  public void updateOrderStatus(int orderId, OrderStatus status) {

    orderRepository.updateOrderStatus(orderId, status);

  }

}
