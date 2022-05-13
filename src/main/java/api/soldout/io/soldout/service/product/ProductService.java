package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.service.product.command.SaveCommand;
import java.util.Collection;

/**
 * .
 */

public interface ProductService {

  ProductDto save(SaveCommand command);

  Collection<ProductDto> findAllProduct();

  void clear();

}
