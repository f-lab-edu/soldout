package api.soldout.io.soldout.repository.product;

import api.soldout.io.soldout.dtos.ProductDto;
import java.util.Collection;

/**
 * .
 */

public interface ProductRepository {

  ProductDto save(ProductDto product);

  Collection<ProductDto> findAllProduct();

  void clear();

}
