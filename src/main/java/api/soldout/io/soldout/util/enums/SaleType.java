package api.soldout.io.soldout.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public enum SaleType {

  SALE_NOW("즉시 판매"),
  SALE_BID("판매 입찰");

  private final String text;
}
