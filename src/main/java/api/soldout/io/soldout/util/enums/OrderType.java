package api.soldout.io.soldout.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public enum OrderType {

  ORDER_NOW("OT_001"),
  ORDER_BID("OT_002");

  private final String code;

}
