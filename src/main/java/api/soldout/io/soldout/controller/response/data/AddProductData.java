package api.soldout.io.soldout.controller.response.data;

import api.soldout.io.soldout.domain.ImageDto;
import api.soldout.io.soldout.domain.ProductDto;
import java.time.LocalDate;
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
  private List<ImageDto> images;

  /**
   * .
   */

  public static AddProductData from(ProductDto product) {

    String name = product.getName();
    String brand = product.getBrand();
    String modelNumber = product.getModelNumber();
    LocalDate releaseDay = product.getReleaseDay();
    String color = product.getColor();
    List<ImageDto> images = product.getImages();

    return new AddProductData(name, brand, modelNumber, releaseDay, color, images);

  }
}
