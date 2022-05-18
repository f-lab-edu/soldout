package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.ProductDto;
import java.util.Collection;

/**
 * ProductMapper.
 * 상품 엔티티에 대한 Sql문을 담당할 객체
 */

//@Mapper
public interface ProductMapper {

  ProductDto save(ProductDto productDto);

  Collection<ProductDto> findAll();

}
