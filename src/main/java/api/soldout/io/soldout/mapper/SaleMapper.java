package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 */

@Mapper
public interface SaleMapper {

  void insertSale(SaleDto saleDto);

  List<SaleDto> findByUserId(int userId);

  List<SaleDto> findByProductId(int productId);

}
