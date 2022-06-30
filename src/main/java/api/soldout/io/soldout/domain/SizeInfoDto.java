package api.soldout.io.soldout.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeInfoDto {

  private int id;
  private int productId;
  private int min;
  private int max;
  private int unit;

}
