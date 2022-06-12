package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.dtos.entity.OrderDto.OrderStatus;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * .
 */

@Mapper
public interface OrderMapper {

  void insertOrder(OrderDto order);

  List<OrderDto> findByUserId(String userId);

  List<OrderDto> findByProductId(String productId);

  void updateOrderStatus(@Param("orderId") int saleId, @Param("status") OrderStatus status);

}
