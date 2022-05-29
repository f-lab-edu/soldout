package api.soldout.io.soldout.dtos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
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

  /**
   * .
   */

  @JsonCreator
  public static OrderType fromCode(String code) {

    for (OrderType tempType : OrderType.values()) {

      if (tempType.getCode().equals(code)) {

        return tempType;

      }

    }

    return null;

  }
}
