package api.soldout.io.soldout.controller.product.request;

import api.soldout.io.soldout.service.product.command.SaveCommand;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveRequest {

  private String name;
  private String brand;
  private String modelNumber;
  private String releaseDay;
  private String color;
  private Collection<String> imagesLink;

  /**
   * .
   */

  public static SaveCommand toCommand(SaveRequest request) {

    return new SaveCommand(
        request.getName(),
        request.getBrand(),
        request.getModelNumber(),
        request.getReleaseDay(),
        request.getColor(),
        request.getImagesLink()
    );

  }
}
