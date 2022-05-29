package api.soldout.io.soldout.dtos.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ProductDto.
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  private int id;
  private String name;
  private String brand;
  private String modelNumber;
  private LocalDate releaseDay;
  private String color;
  private List<ImageDto> images;

  /**
   * .
   */

  public void buildToImageDto(List<String> images) {

    List<ImageDto> imageList = new ArrayList<>();

    for (String link : images) {

      ImageDto image = ImageDto.builder()
          .link(link)
          .build();

      imageList.add(image);

    }

    this.images = imageList;

  }
}
