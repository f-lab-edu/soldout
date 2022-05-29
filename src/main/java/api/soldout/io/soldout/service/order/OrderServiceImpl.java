package api.soldout.io.soldout.service.order;


import api.soldout.io.soldout.dtos.OrderDto;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public void order(OrderCommand command) {

    OrderDto order = OrderDto.builder()
        .build();

    orderRepository.order(order);

  }
}
