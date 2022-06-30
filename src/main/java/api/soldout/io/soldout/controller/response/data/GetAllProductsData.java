package api.soldout.io.soldout.controller.response.data;

import api.soldout.io.soldout.domain.ProductDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class GetAllProductsData {

  private List<ProductDto> productList;

  /**
   * .
   */

  public static GetAllProductsData from(List<ProductDto> productList) {

    for (ProductDto product : productList) {
      product.getImages();
    }

    return new GetAllProductsData(productList);

  }

}
