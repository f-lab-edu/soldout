package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.util.Collection;

/**
 * .
 */

public interface ProductService {

  ProductDto addProduct(AddProductCommand command);

  Collection<ProductDto> findAll();

  void clear();

}
