package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.domain.OrderDto;
import api.soldout.io.soldout.domain.OrderDto.OrderStatus;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

  void insertOrder(OrderDto order);

  List<OrderDto> findByUserId(int userId);

  List<OrderDto> findByProductId(int productId);

  void updateOrderStatus(@Param("orderId") int orderId, @Param("status") OrderStatus status);

}
