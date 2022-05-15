package api.soldout.io.soldout.dtos.response.data;

import api.soldout.io.soldout.dtos.ProductDto;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class GetAllProductsData {

  private Collection<ProductDto> imagesLink;

  /**
   * .
   */

  public static GetAllProductsData from(Collection<ProductDto> imagesLink) {

    return new GetAllProductsData(imagesLink);

  }

}
