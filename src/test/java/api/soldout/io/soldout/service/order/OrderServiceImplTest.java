package api.soldout.io.soldout.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.domain.OrderDto;
import api.soldout.io.soldout.domain.OrderDto.OrderStatus;
import api.soldout.io.soldout.domain.OrderDto.OrderType;
import api.soldout.io.soldout.listener.event.OrderCreated;
import api.soldout.io.soldout.repository.order.OrderRepository;
import api.soldout.io.soldout.repository.order.OrderRepositoryImpl;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import java.util.ArrayList;
import java.util.List;
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

  int orderId = 1;
  int userId = 1;
  int productId = 1;
  int size = 250;
  int price = 100000;
  int period = 3;
  OrderStatus status = OrderStatus.MATCHING_COMPLETE;

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
        userId, productId, size, price, period, OrderType.ORDER_NOW
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

  @Test
  @DisplayName("사용자 Id로 구매 입찰 목록 조회")
  void findByUserIdTest() throws Exception {
    // given
    OrderDto orderDto = OrderDto.builder()
        .userId(userId)
        .build();

    List<OrderDto> list = new ArrayList<>();
    list.add(orderDto);


    // when
    when(orderRepository.findByUserId(userId)).thenReturn(list);

    List<OrderDto> findList = orderService.findByUserId(userId);

    // then
    verify(orderRepository, times(1)).findByUserId(userId);

    assertThat(findList.size()).isEqualTo(list.size());

  }

  @Test
  @DisplayName("제품 Id로 구매 입찰 목록 조회")
  void findByProductIdTest() throws Exception {
    // given
    OrderDto orderDto = OrderDto.builder()
        .productId(productId)
        .build();

    List<OrderDto> list = new ArrayList<>();
    list.add(orderDto);

    // when
    when(orderRepository.findByProductId(productId)).thenReturn(list);

    List<OrderDto> findList = orderService.findByProductId(productId);

    // then
    verify(orderRepository, times(1)).findByProductId(productId);

    assertThat(findList.size()).isEqualTo(list.size());

  }

  @Test
  @DisplayName("구매 상태 수정 로직 테스트")
  void updateSaleStatusTest() throws Exception {
    // given

    // when
    orderService.updateOrderStatus(orderId, status);

    // then
    verify(orderRepository, times(1)).updateOrderStatus(orderId, status);

  }
}