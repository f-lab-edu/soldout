package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 */

@Mapper
public interface OrderMapper {

  void insertOrder(OrderDto order);

  OrderDto findByOrderId(String orderId);

  OrderDto findByUserId(String userId);

  OrderDto findByProductId(String productId);

}
