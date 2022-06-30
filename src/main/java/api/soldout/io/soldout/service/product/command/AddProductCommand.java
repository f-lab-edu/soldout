package api.soldout.io.soldout.service.product.command;

import api.soldout.io.soldout.enums.ProductCategory;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class AddProductCommand {

  private ProductCategory category;
  private String name;
  private String brand;
  private String modelNumber;
  private LocalDate releaseDay;
  private String color;
  private Map<String, Integer> sizeInfo;
  private List<String> images;

}
