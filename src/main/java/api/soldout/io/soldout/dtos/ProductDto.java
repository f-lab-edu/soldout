package api.soldout.io.soldout.dtos;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * ProductDto.
 */

@Getter
@Builder
@AllArgsConstructor
public class ProductDto {

  private int id;
  private String name;
  private String brand;
  private String modelNumber;
  private LocalDate releaseDay;
  private String color;
  private List<String> images;

}
