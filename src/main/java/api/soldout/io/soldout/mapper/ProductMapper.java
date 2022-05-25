package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.ImageDto;
import api.soldout.io.soldout.dtos.ProductDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ProductMapper.
 * 상품 엔티티에 대한 Sql문을 담당할 객체
 */

@Mapper
public interface ProductMapper {

  void insertProduct(ProductDto productDto);

  void insertImages(@Param("list") List<ImageDto> imageList, @Param("productId") int productId);

  List<ProductDto> findAllProducts();

}
