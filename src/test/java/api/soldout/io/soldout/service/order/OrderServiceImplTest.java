package api.soldout.io.soldout.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.dtos.entity.OrderDto.OrderStatus;
import api.soldout.io.soldout.listener.event.OrderCreated;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.repository.order.OrderRepositoryImpl;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import api.soldout.io.soldout.util.enums.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  OrderService orderService;

  OrderRepository orderRepository;

  ApplicationEventPublisher eventPublisher;

  @BeforeEach
  void init() {

    orderRepository = mock(OrderRepositoryImpl.class);

    eventPublisher = mock(ApplicationEventPublisher.class);

    orderService = new OrderServiceImpl(orderRepository, eventPublisher);

  }

  @Test
  @DisplayName("즉시 구매 테스트")
  void orderNowTest() {
    // given
    OrderCommand command = new OrderCommand(
        1, 1, 250, 100000, 3, OrderType.ORDER_NOW
    );

    final ArgumentCaptor<OrderDto> orderCap = ArgumentCaptor.forClass(OrderDto.class);

    final ArgumentCaptor<OrderCreated> eventCap = ArgumentCaptor.forClass(OrderCreated.class);


    // when
    orderService.orderNow(command);

    // then
    verify(orderRepository, times(1))
        .saveOrder(orderCap.capture());

    OrderDto order = orderCap.getValue();

    assertThat(order.getUserId()).isEqualTo(command.getUserId());
    assertThat(order.getProductId()).isEqualTo(command.getProductId());
    assertThat(order.getSize()).isEqualTo(command.getSize());
    assertThat(order.getPrice()).isEqualTo(command.getPrice());
    assertThat(order.getType()).isEqualTo(command.getType());
    assertThat(order.getStatus()).isEqualTo(OrderStatus.BID_PROGRESS);

    verify(eventPublisher, times(1))
        .publishEvent(eventCap.capture());

    OrderCreated event = eventCap.getValue();

    assertThat(event.getOrderId()).isEqualTo(order.getId());
    assertThat(event.getProductId()).isEqualTo(order.getProductId());
    assertThat(event.getSize()).isEqualTo(order.getSize());
    assertThat(event.getPrice()).isEqualTo(order.getPrice());

  }

}