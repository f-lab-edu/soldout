package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.dtos.entity.OrderDto;
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
  public void order(OrderDto order) {

    orderMapper.insertOrder(order);

  }

  @Override
  public List<OrderDto> findByOrderId(String orderId) {

    return orderMapper.findByOrderId(orderId);

  }

  @Override
  public List<OrderDto> findByUserId(String userId) {

    return orderMapper.findByUserId(userId);

  }

  @Override
  public List<OrderDto> findByProductId(String productId) {

    return orderMapper.findByProductId(productId);

  }
}
