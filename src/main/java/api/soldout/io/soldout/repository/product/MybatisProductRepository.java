package api.soldout.io.soldout.repository.product;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisProductRepository implements ProductRepository {

  private final ProductMapper productMapper;

  @Override
  public ProductDto save(ProductDto product) {

    return productMapper.save(product);

  }

  @Override
  public List<ProductDto> findAll() {

    return productMapper.findAll();

  }
}
