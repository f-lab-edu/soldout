package api.soldout.io.soldout.controller.product.request;

import api.soldout.io.soldout.service.product.command.AddProductCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
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
public class AddProductRequest {

  private String name;
  private String brand;
  private String modelNumber;
  @JsonFormat(pattern = "yyyy/MM/dd")
  private LocalDate releaseDay;
  private String color;
  private Collection<String> imagesLink;

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
        request.getImagesLink()
    );

  }
}
