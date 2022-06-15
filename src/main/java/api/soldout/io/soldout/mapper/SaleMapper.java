package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * .
 */

@Mapper
public interface SaleMapper {

  void insertSale(SaleDto saleDto);

  List<SaleDto> findByUserId(int userId);

  List<SaleDto> findByProductId(int productId);

  List<SaleDto> findByProductIdAndSizeAndPriceAndSaleStatus(
      @Param("productId") int productId,
      @Param("size") int size,
      @Param("price") int price,
      @Param("status") SaleStatus status);

  void updateSaleStatus(@Param("saleId") int saleId, @Param("status") SaleStatus status);

}
