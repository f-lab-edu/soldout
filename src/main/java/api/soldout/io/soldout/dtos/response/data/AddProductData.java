package api.soldout.io.soldout.dtos.response.data;

import api.soldout.io.soldout.dtos.ProductDto;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
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
  private List<String> images;

  /**
   * .
   */

  public static AddProductData from(ProductDto product) {

    String name = product.getName();
    String brand = product.getBrand();
    String modelNumber = product.getModelNumber();
    LocalDate releaseDay = product.getReleaseDay();
    String color = product.getColor();
    List<String> images = product.getImages();

    return new AddProductData(name, brand, modelNumber, releaseDay, color, images);

  }
}
