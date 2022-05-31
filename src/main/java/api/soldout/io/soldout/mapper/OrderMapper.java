package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.OrderDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 */

@Mapper
public interface OrderMapper {

  void insertOrder(OrderDto order);

  List<OrderDto> findByUserId(String userId);

  List<OrderDto> findByProductId(String productId);

}
