package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.domain.OrderDto;
import api.soldout.io.soldout.domain.OrderDto.OrderStatus;
import api.soldout.io.soldout.mapper.OrderMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderMapper orderMapper;

  @Override
  public void saveOrder(OrderDto order) {

    orderMapper.insertOrder(order);

  }

  @Override
  public List<OrderDto> findByUserId(int userId) {

    return orderMapper.findByUserId(userId);

  }

  @Override
  public List<OrderDto> findByProductId(int productId) {

    return orderMapper.findByProductId(productId);

  }

  @Override
  public void updateOrderStatus(int orderId, OrderStatus status) {

    orderMapper.updateOrderStatus(orderId, status);

  }
}
