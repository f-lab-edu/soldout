package api.soldout.io.soldout.controller.product.request;

import api.soldout.io.soldout.service.product.command.AddProductCommand;
import api.soldout.io.soldout.util.enums.ProductCategory;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

  @NotNull
  private ProductCategory category;

  @NotEmpty
  private String name;

  @NotEmpty
  private String brand;

  @NotEmpty
  private String modelNumber;

  @NotNull
  private LocalDate releaseDay;

  @NotEmpty
  private String color;

  @NotEmpty
  @Size(min = 3, message = "사이즈 정보를 입력하세요")
  private Map<String, Integer> sizeInfo;

  @NotEmpty
  @Size(min = 1, message = "이미지 링크는 최소 한개 이상 입력하세요.")
  private List<String> images;

  /**
   * Request 객체가 Service Layer 에 의존하지 않도록 Command 객체로 변환해줍니다.
   * Service 입장에서 매개변수로 넘어오는 타입이 Request 객체가 되지 않고 그 존재를 몰라야 유연한 설계가 가능하기 때문입니다.
   */

  public static AddProductCommand toCommand(AddProductRequest request) {

    return new AddProductCommand(
        request.getCategory(),
        request.getName(),
        request.getBrand(),
        request.getModelNumber(),
        request.getReleaseDay(),
        request.getColor(),
        request.getSizeInfo(),
        request.getImages()
    );

  }
}
