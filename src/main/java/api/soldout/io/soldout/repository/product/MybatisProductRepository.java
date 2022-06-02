package api.soldout.io.soldout.repository.product;

import api.soldout.io.soldout.dtos.entity.ProductDto;
import api.soldout.io.soldout.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 */

@Slf4j
@RequiredArgsConstructor
public class MybatisProductRepository implements ProductRepository {

  private final ProductMapper productMapper;

  @Override
  public void save(ProductDto product) {

    productMapper.insertProduct(product);

    productMapper.insertImages(product.getImages(), product.getId());

    productMapper.insertSizeInfo(product.getSizeInfo(), product.getId());

  }

  @Override
  public List<ProductDto> findAll() {

    return productMapper.findAllProducts();

  }
}
