package api.soldout.io.soldout.dtos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public enum OrderEndDay {

  THREE_DAY("OED_001"),
  WEEK("OED_002"),
  HALF_MONTH("OED_003"),
  MONTH("OED_004");

  private final String code;

  /**
   * .
   */

  @JsonCreator
  public static OrderEndDay fromCode(String code) {

    for (OrderEndDay tempDays : OrderEndDay.values()) {

      if (tempDays.getCode().equals(code)) {

        return tempDays;

      }

    }

    return null;

  }

}
