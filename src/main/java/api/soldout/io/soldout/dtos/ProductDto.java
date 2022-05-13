package api.soldout.io.soldout.dtos;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * ProductDto.
 * 상품 엔티티 클래스
 */

@Getter
@Builder
@AllArgsConstructor
public class ProductDto {

  private String name;
  private String brand;
  private String modelNumber;
  private String releaseDay;
  private String color;
  private Collection<String> imagesLink;

}
