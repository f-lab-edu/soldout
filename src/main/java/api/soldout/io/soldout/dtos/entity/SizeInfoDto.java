package api.soldout.io.soldout.dtos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * .
 */

@Getter
@Builder
@AllArgsConstructor
public class SizeInfoDto {

  private int id;
  private int productId;
  private int min;
  private int max;
  private int unit;

}
