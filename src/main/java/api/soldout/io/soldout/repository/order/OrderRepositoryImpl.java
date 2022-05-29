package api.soldout.io.soldout.repository.order;

import api.soldout.io.soldout.dtos.OrderDto;
import api.soldout.io.soldout.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderMapper orderMapper;

  @Override
  public void order(OrderDto order) {

    orderMapper.insertOrder();

  }
}
