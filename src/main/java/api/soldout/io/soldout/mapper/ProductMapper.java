package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.ImageDto;
import api.soldout.io.soldout.dtos.entity.ProductDto;
import api.soldout.io.soldout.dtos.entity.SizeInfoDto;
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

  void insertImages(@Param("productId") int productId, @Param("list") List<ImageDto> imageList);

  void insertSizeInfo(@Param("productId") int productId, SizeInfoDto sizeInfo);

  List<ProductDto> findAllProducts();

}
