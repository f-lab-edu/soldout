package api.soldout.io.soldout.service.product.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

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

  /**
   * .
   */

  public static LocalDate toLocalDate(String releaseDay) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    return LocalDate.parse(releaseDay, formatter);

  }

}
