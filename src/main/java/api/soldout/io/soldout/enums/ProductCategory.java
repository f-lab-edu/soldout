package api.soldout.io.soldout.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품 카테고리를 구분하기 위한 Enum 클래스.
 */

@Getter
@AllArgsConstructor
public enum ProductCategory {

  SHOES("신발"),
  CLOTHES("의류"),
  ACCESSORY("패션잡화"),
  TECH("테크"),
  LIFE("라이프");

  private final String text;

}
