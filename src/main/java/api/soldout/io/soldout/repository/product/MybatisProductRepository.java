package api.soldout.io.soldout.repository.product;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.mapper.ProductMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Slf4j
@Repository("productRepository")
@RequiredArgsConstructor
public class MybatisProductRepository implements ProductRepository {

  private final ProductMapper productMapper;

  @Override
  public ProductDto save(ProductDto product) {

    return productMapper.save(product);

  }

  @Override
  public Collection<ProductDto> findAll() {

    return productMapper.findAll();

  }
}
