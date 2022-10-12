package api.soldout.io.soldout.service.order;

import api.soldout.io.soldout.domain.OrderDto;
import api.soldout.io.soldout.domain.OrderDto.OrderStatus;
import api.soldout.io.soldout.listener.event.OrderCreated;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  @Transactional
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

        OrderCreated.from(order.getId(), order.getProductId(), order.getSize(), order.getPrice()

        )

    );

  }

  @Override
  @Transactional(readOnly = true)
  public List<OrderDto> findByUserId(int userId) {

    return orderRepository.findByUserId(userId);

  }

  @Override
  @Transactional(readOnly = true)
  public List<OrderDto> findByProductId(int productId) {

    return orderRepository.findByProductId(productId);

  }

  @Override
  @Transactional
  public void updateOrderStatus(int orderId, OrderStatus status) {

    orderRepository.updateOrderStatus(orderId, status);

  }

}
