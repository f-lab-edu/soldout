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
  public void saveProduct(ProductDto product) {

    productMapper.insertProduct(product);

    productMapper.insertSizeInfo(product.getSizeInfo(), product.getId());

    productMapper.insertImages(product.getImages(), product.getId());

  }

  @Override
  public List<ProductDto> findAll() {

    return productMapper.findAllProducts();

  }
}
