package api.soldout.io.soldout.service.product.command;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class SaveCommand {

  private String name;
  private String brand;
  private String modelNumber;
  private String releaseDay;
  private String color;
  private Collection<String> imagesLink;

}
