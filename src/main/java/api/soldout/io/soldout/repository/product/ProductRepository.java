package api.soldout.io.soldout.repository.product;

import api.soldout.io.soldout.domain.ProductDto;
import java.util.List;

public interface ProductRepository {

  void saveProduct(ProductDto product);

  List<ProductDto> findAll();

}
