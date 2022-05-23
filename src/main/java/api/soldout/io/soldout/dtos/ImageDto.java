package api.soldout.io.soldout.dtos;

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
public class ImageDto {
  private int id;
  private int productId;
  private String link;
}
