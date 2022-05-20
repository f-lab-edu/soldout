package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.util.Collection;
import java.util.List;

/**
 * .
 */

public interface ProductService {

  ProductDto addProduct(AddProductCommand command);

  List<ProductDto> findAll();

}
