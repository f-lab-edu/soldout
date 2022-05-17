package api.soldout.io.soldout.service.product.command;

import java.time.LocalDate;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class AddProductCommand {

  private String name;
  private String brand;
  private String modelNumber;
  private LocalDate releaseDay;
  private String color;
  private Collection<String> imagesLink;

}
