package api.soldout.io.soldout.dtos.response.data;

import api.soldout.io.soldout.dtos.ProductDto;
import java.time.LocalDate;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class AddProductData {

  private String name;
  private String brand;
  private String modelNumber;
  private LocalDate releaseDay;
  private String color;
  private Collection<String> imagesLink;

  /**
   * .
   */

  public static AddProductData from(ProductDto product) {

    String name = product.getName();
    String brand = product.getBrand();
    String modelNumber = product.getModelNumber();
    LocalDate releaseDay = product.getReleaseDay();
    String color = product.getColor();
    Collection<String> imagesLink = product.getImagesLink();

    return new AddProductData(name, brand, modelNumber, releaseDay, color, imagesLink);
  }
}
