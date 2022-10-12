package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.domain.ImageDto;
import api.soldout.io.soldout.domain.ProductDto;
import api.soldout.io.soldout.domain.SizeInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ProductMapper.
 */

@Mapper
public interface ProductMapper {

  void insertProduct(ProductDto productDto);

  void insertImages(@Param("list") List<ImageDto> imageList, @Param("productId") int productId);

  void insertSizeInfo(@Param("sizeInfo") SizeInfoDto sizeInfo, @Param("productId") int productId);

  List<ProductDto> findAllProducts();

}
