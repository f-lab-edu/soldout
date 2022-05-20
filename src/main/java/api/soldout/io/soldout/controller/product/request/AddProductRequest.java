package api.soldout.io.soldout.controller.product.request;

import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductRequest {

  private String name;
  private String brand;
  private String modelNumber;
  private LocalDate releaseDay;
  private String color;
  private List<String> images;

  /**
   * .
   */

  public static AddProductCommand toCommand(AddProductRequest request) {

    return new AddProductCommand(
        request.getName(),
        request.getBrand(),
        request.getModelNumber(),
        request.getReleaseDay(),
        request.getColor(),
        request.getImages()
    );

  }
}
